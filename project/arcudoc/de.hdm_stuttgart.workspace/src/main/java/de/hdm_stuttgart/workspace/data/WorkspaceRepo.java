package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.AccountInformation;
import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.workspace.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

//todo present error messages to the user
public class WorkspaceRepo {

    private static final Logger log = LogManager.getLogger(WorkspaceRepo.class);
    private final SupabaseRestClient supabaseRestClient = ServiceProvider.getSupabaseRestClient(); //todo inject

    // - - - - projects - - - -

    /**
     * calls related api endpoints and methods which are required when creating a new project
     * this includes: create the project, invite project members,
     *
     * @param projectTitle title the project should have
     * @param memberMails  list of mails for inviting project members
     */
    public void createProject(String projectTitle, List<String> memberMails) {

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
                        //inviteMembers(memberMails, projectId);
                    }
                } else {
                    log.error(response.code() + " - Project creation not successful");
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
     * calls corresponding api endpoints for adding member mails on invitations list
     *
     //* @param invitationMailsStrings list of strings representing a member mail each
     * @param projectId              the id of the project the members should be invited for
     */
    public void inviteMembers(List<ProjectMember> members, int projectId) {

       /* List<InvitationRequest> invitationRequestsList = invitationMailsStrings.stream()
                .map(s -> new InvitationRequest(s, projectId)).toList();*/

        List<InvitationRequest> invitationRequests = members.stream()
                .map(m -> new InvitationRequest(projectId,m)).toList();

        Call<Void> call = supabaseRestClient.createNewProjectInvitation(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                invitationRequests
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    log.debug(response.code() + " - Invitations added successfully");
                } else {
                    log.error(response.code() + " - Invitation not successful");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Invitation failed");
            }
        });
    }

    /**
     * calls corresponding api endpoints for getting open project invitations for the user (based on the email)
     */
    public void getInvitations() {

        Call<List<InvitationResponse>> call = supabaseRestClient.getProjectInvitations(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "eq." + AccountInformation.getInstance().getUserMail(),
                "*,projects(title)"
        );

        call.enqueue(new Callback<List<InvitationResponse>>() {
            @Override
            public void onResponse(Call<List<InvitationResponse>> call, Response<List<InvitationResponse>> response) {
                if (response.isSuccessful()) {
                    log.debug("Invitations fetches successfully");
                    List<InvitationResponse> invitations = response.body();
                    InvitationResponse temp = invitations.get(0);
                    System.out.println(temp);
                } else {
                    log.error(response.code() + " - Fetching invitations not successful");
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
     * @param projectId the id of the project the invitation should be accepted for
     */
    public void acceptProjectInvitation(int projectId){

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
                        addProjectMember(projectId,projectMembers.get(0));
                        deleteProjectInvitation(projectId);
                    }else{
                        log.error("Empty or null response for fetching project invitation for project with id " + projectId);
                    }
                }else{
                    log.error(response.code() + "Getting single invitation for project " + projectId + " not successful");
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
                    log.error(response.code() + " - Delete project invitation not successful");
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
     * calls corresponding api endpoints for adding current user as member to a project
     *
     * @param projectId the id of the project where the member should be added
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
                } else {
                    log.error(response.code() + " - Project member adding not successful");
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
     */
    public void getMemberProjects() {

        Call<List<MemberProjectResponse>> call = supabaseRestClient.getMemberProjects(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "projects(title,id)" //indicates to select title and id of project table although project_members table is queried in request (linked in supabase)
        );

        call.enqueue(new Callback<List<MemberProjectResponse>>() {
            @Override
            public void onResponse(Call<List<MemberProjectResponse>> call, Response<List<MemberProjectResponse>> response) {
                if (response.isSuccessful()) {
                    log.debug("Member projects fetched successfully");
                    List<MemberProjectResponse> projects = response.body();
                } else {
                    log.error(response.code() + " - Fetching member projects not successful");
                }
            }

            @Override
            public void onFailure(Call<List<MemberProjectResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Fetching member projects failed");
            }
        });
    }

    public static void main(String[] args) {
        WorkspaceRepo w = new WorkspaceRepo();

       // ProjectMember p = new ProjectMember("piaa@mail.com","developer",ProjectRole.EDITOR.getSupabaseName());
       // ProjectMember p1 = new ProjectMember("saraa@mail.com","designer",ProjectRole.EDITOR.getSupabaseName());
        List<ProjectMember> list = new ArrayList<>();
       // list.add(p);
        //list.add(p1);
        //w.inviteMembers(list,10);
        //w.addProjectMember(10, p);

        w.acceptProjectInvitation(10);
    }

}
