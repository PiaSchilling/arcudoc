package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.workspace.model.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;

//todo present error messages to the user
public class WorkspaceRepo {

    private static final Logger log = LogManager.getLogger(WorkspaceRepo.class);
    private final SupabaseRestClient supabaseRestClient = ServiceProvider.getSupabaseRestClient(); //todo inject

    // observable properties
    private final ListProperty<InvitationResponse> projectInvitationsProperty = new SimpleListProperty<>();
    private final ListProperty<MemberProjectResponse> memberProjectsProperty = new SimpleListProperty<>();
    private final ObjectProperty<NetworkStatus> networkStatusObjectProperty = new SimpleObjectProperty<>();


    // - - - - projects - - - -

    /**
     * calls related api endpoints and methods which are required when creating a new project
     * this includes: create the project and invite project members
     *
     * @param projectTitle title the project should have
     * @param invitationMembers  list of projectMembers which should be invited to the project
     */
    public void createProject(String projectTitle, List<ProjectMember> invitationMembers) {

        Call<List<ProjectResponse>> call = supabaseRestClient.createNewProject(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                "return=representation",
                new ProjectRequest(projectTitle));

        call.enqueue(new Callback<List<ProjectResponse>>() {
            @Override
            public void onResponse(Call<List<ProjectResponse>> call, Response<List<ProjectResponse>> response) {
                if (response.isSuccessful()) {
                    log.debug(response.code() + " - Project created successfully");
                    List<ProjectResponse> createdProject = response.body();
                    if (createdProject != null) {
                        int projectId = createdProject.get(0).getId();
                        inviteMembers(invitationMembers, projectId);
                    }
                } else {
                    log.error(response.code() + response.message() + " - Project creation not successful");
                    //todo check codes and inform user about what kind of error occurred
                }
            }

            @Override
            public void onFailure(Call<List<ProjectResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Project creation not successful");
            }
        });
    }

    // - - - - project invitations - - - -

    /**
     * calls corresponding api endpoints to invite a member to a project
     * if member is alreay invited to the project, invitation will be replaced by a new one
     *
     * @param members list of members which should be invited
     * @param projectId id of the project the members should be invited to
     */
    public void inviteMembers(List<ProjectMember> members, int projectId) {

        List<InvitationRequest> invitationRequests = members.stream()
                .map(m -> new InvitationRequest(projectId,m)).toList();

        Call<Void> call = supabaseRestClient.createNewProjectInvitationReturnMinimal(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                "return=minimal,",
                invitationRequests
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    log.debug(response.code() + " - Invitations added successfully");
                } else {
                    log.error(response.code() +  response.message() + " - Invitation not successful");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Invitation failed");
            }
        });
    }

    /**
     * calls corresponding api endpoints for getting open project invitations for the user
     * filtering is done by row-level-policies in supabase (only invitations for user-mail provided by jwt are returned)
     */
    public void fetchProjectInvitations() {

        Call<List<InvitationResponse>> call = supabaseRestClient.getProjectInvitations(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "*,projects(title)"
        );

        call.enqueue(new Callback<List<InvitationResponse>>() {
            @Override
            public void onResponse(Call<List<InvitationResponse>> call, Response<List<InvitationResponse>> response) {
                if (response.isSuccessful()) {
                    log.debug("Invitations fetches successfully");
                    List<InvitationResponse> invitations = response.body();
                    if(invitations != null){
                        ObservableList<InvitationResponse> observableList = FXCollections.observableArrayList();
                        observableList.addAll(invitations);
                        projectInvitationsProperty.setValue(observableList);
                    }else{
                        log.error("InvitationList is null");
                    }
                } else {
                    log.error(response.code() + response.message() + " - Fetching invitations not successful");
                    //todo if 401 jwt expired -> fetch new token with refresh token
                }
            }

            @Override
            public void onFailure(Call<List<InvitationResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Fetching invitations failed");
            }
        });
    }

    /**
     * calls related methods for accepting a project invitation:
     * get the invitation, add the currently authenticated user to project-members and finally delete the open project invitation
     *
     * @param projectId the id of the project the invitation should be accepted for
     */
    public void respondProjectInvitation(int projectId, boolean isAccepted){ //todo this should be moved to supabase (can be handled with trigger)

        Call<List<ProjectMember>> call = supabaseRestClient.getSingleProjectInvitationByProjectId(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "eq." + projectId,
                "*"
        );

        call.enqueue(new Callback<List<ProjectMember>>() {
            @Override
            public void onResponse(Call<List<ProjectMember>> call, Response<List<ProjectMember>> response) {
                if(response.isSuccessful()){
                    List<ProjectMember> projectMembers = response.body();
                    if(projectMembers != null && projectMembers.size() > 0){

                        if(isAccepted){
                            addProjectMember(projectId,projectMembers.get(0)); //only add project member if invitation is accepted
                            deleteProjectInvitation(projectId);

                            NetworkStatus.SUCCESS.setNetworkMessage("Invitation accepted");
                            networkStatusObjectProperty.setValue(NetworkStatus.SUCCESS);
                        }else{
                            deleteProjectInvitation(projectId); //if not accepted only delete invitation

                            NetworkStatus.SUCCESS.setNetworkMessage("Invitation declined");
                            networkStatusObjectProperty.setValue(NetworkStatus.SUCCESS);
                        }

                    }else{
                        log.error("Empty or null response for fetching project invitation for project with id " + projectId);

                    }
                }else{
                    log.error(response.code() + response.message() + "Getting single invitation for project " + projectId + " not successful");
                    NetworkStatus.FAIL.setNetworkMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ProjectMember>> call, Throwable throwable) {
                log.error(throwable.getMessage() + "Getting single invitation for project " + projectId + " failed");
            }
        });
    }

    /**
     * calls corresponding api endpoints for deleting an open project invitation
     * filtering is done by row-level-policies in supabase (only invitation for user-mail provided by jwt are deleted)
     *
     * @param projectId the id of the project the invitation should be deleted for
     */
    public void deleteProjectInvitation(int projectId) {

        Call<Void> call = supabaseRestClient.deleteProjectInvitation(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "eq." + projectId
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    log.debug("Delete project invitation successful");
                } else {
                    log.error(response.code() + response.message() + " - Delete project invitation not successful");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Delete project invitation failed");
            }
        });
    }

    // - - - - project members - - - -

    /**
     * call corresponding api endpoints to add a specific member to a specific project
     *
     * @param projectId the id of the project where the member should be added
     * @param projectMember the project member which should be added
     */
    public void addProjectMember(int projectId, ProjectMember projectMember) {

        Call<Void> call = supabaseRestClient.addProjectMember(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                "return=representation",
                new MemberRequest(projectId, projectMember)
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    log.debug("Project member added successfully");
                    fetchMemberProjects(); //update memberProjects
                } else {
                    log.error(response.code() + response.message() + " - Project member adding not successful");
                    if(response.code() == 409){
                        NetworkStatus.FAIL.setNetworkMessage(projectMember.getMail() + "is already member of the project");
                        networkStatusObjectProperty.setValue(NetworkStatus.FAIL);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Add project member failed");
            }
        });
    }

    /**
     * calls corresponding api endpoints for getting the projects the user is already a member of
     * filtering is done by row-level-policies in supabase (only projects for user id provided by jwt are returned)
     */
    public void fetchMemberProjects() {

        Call<List<MemberProjectResponse>> call = supabaseRestClient.getMemberProjects(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "projects(title,last_updated,id,profiles!projects_owner_fkey(mail,display_name)),project_role,job_label" //indicates to select title and id of project table although project_members table is queried in request (linked in supabase)
        );

        call.enqueue(new Callback<List<MemberProjectResponse>>() {
            @Override
            public void onResponse(Call<List<MemberProjectResponse>> call, Response<List<MemberProjectResponse>> response) {
                if (response.isSuccessful()) {
                    log.debug("Member projects fetched successfully");
                    List<MemberProjectResponse> memberProjects = response.body();
                    if(memberProjects != null){
                        System.out.println(Arrays.toString(memberProjects.toArray()));

                        ObservableList<MemberProjectResponse> observableList = FXCollections.observableArrayList();
                        observableList.addAll(memberProjects);
                        memberProjectsProperty.setValue(observableList);
                    }

                } else {
                    log.error(response.code() + response.message() + " - Fetching member projects not successful");
                }
            }

            @Override
            public void onFailure(Call<List<MemberProjectResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Fetching member projects failed");
            }
        });
    }

    // - - - - - property getters - - - - -

    public ListProperty<InvitationResponse> getProjectInvitationsProperty() {
        return projectInvitationsProperty;
    }

    public ListProperty<MemberProjectResponse> getMemberProjectsProperty() {
        return memberProjectsProperty;
    }

    public ObjectProperty<NetworkStatus> getNetworkStatusObjectProperty() {
        return networkStatusObjectProperty;
    }

}
