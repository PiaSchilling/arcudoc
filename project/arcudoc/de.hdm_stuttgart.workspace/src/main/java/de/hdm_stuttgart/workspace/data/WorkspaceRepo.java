package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.AccountInformation;
import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.workspace.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                        inviteMembers(memberMails, projectId);
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
     * @param invitationMailsStrings list of strings representing a member mail each
     * @param projectId              the id of the project the members should be invited for
     */
    public void inviteMembers(List<String> invitationMailsStrings, int projectId) {

        List<InvitationRequest> invitationRequestsList = invitationMailsStrings.stream()
                .map(s -> new InvitationRequest(s, projectId)).toList();

        Call<Void> call = supabaseRestClient.createNewProjectInvitation(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                invitationRequestsList
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
     * accept an open project invitation: first delete open invitation and add member to project members
     *
     * @param projectId the id of the project the invitation should be accepted for
     */
    public void acceptInvitation(int projectId) {
        deleteProjectInvitation(projectId);
        addProjectMember(projectId);
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
                "eq." + AccountInformation.getInstance().getUserMail(),
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
     * calls corresponding api endpoints for adding member to a project
     *
     * @param projectId the id of the project where the member should be added
     */
    public void addProjectMember(int projectId) {

        Call<Void> call = supabaseRestClient.addProjectMember(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                "return=representation",
                new MemberRequest(projectId)
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

}
