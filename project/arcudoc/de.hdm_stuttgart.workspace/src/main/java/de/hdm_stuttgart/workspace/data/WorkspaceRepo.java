package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.workspace.model.InvitationRequest;
import de.hdm_stuttgart.workspace.model.InvitationResponse;
import de.hdm_stuttgart.workspace.model.ProjectRequest;
import de.hdm_stuttgart.workspace.model.ProjectResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//todo present error messages to the user
public class WorkspaceRepo {

    private static final Logger log = LogManager.getLogger(WorkspaceRepo.class);
    private final SupabaseRestClient supabaseRestClient = ServiceProvider.getSupabaseRestClient(); //todo inject

    /**
     * calls related api endpoints and methods which are required when creating a new project
     * this includes: create the project, invite project members,
     * @param projectTitle title the project should have
     * @param invitationMails list of mails for inviting project members
     */
    public void createProject(String projectTitle, List<String> invitationMails ){

        Call<List<ProjectResponse>> call = supabaseRestClient.createNewProject(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                "return=representation",
                new ProjectRequest(projectTitle));

        call.enqueue(new Callback<List<ProjectResponse>>() {
            @Override
            public void onResponse(Call<List<ProjectResponse>> call, Response<List<ProjectResponse>> response) {
                if(response.isSuccessful()){
                    log.debug(response.code() + " - Project created successfully");
                    List<ProjectResponse> createdProject = response.body();
                    if (createdProject != null) {
                        int projectId = createdProject.get(0).getId();
                        inviteMembers(invitationMails,projectId);
                    }
                }else{
                    log.error(response.code() + " - Project creation not successful");
                    //todo check codes and inform user about what kind of error occurred
                }
            }

            @Override
            public void onFailure(Call<List<ProjectResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Project creation not successful");
            }
        });

        //insert into projects
        //insert owner into project members
        //insert owner into members //todo move to login
        //insert emails into invitations
    }

    /**
     * calls corresponding api endpoints for adding member mails on invitations list
     //* @param invitationMails array of invitation mails wrapped in InvitationsRequest objects for json parsing
     */
    public void inviteMembers(List<String> invitationMailsStrings, int projectId){

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
                if(response.isSuccessful()){
                    log.debug(response.code() + " - Invitations added successfully");
                }else{
                    log.error(response.code() + " - Invitation not successful");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Invitation failed");
            }
        });
    }

    public void addProjectMember(){

    }

    /**
     * shows the open project invitations for the user (based on the email)
     */
    public void getInvitations(){

        Call<List<InvitationResponse>> call = supabaseRestClient.getProjectInvitations(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "eq.pia@gmail.com",
                "*,projects(title)"
        );

        call.enqueue(new Callback<List<InvitationResponse>>() {
            @Override
            public void onResponse(Call<List<InvitationResponse>> call, Response<List<InvitationResponse>> response) {
                if(response.isSuccessful()){
                    List<InvitationResponse> invitations = response.body();
                    InvitationResponse temp = invitations.get(0);
                    System.out.println(temp);
                }else{
                    log.error(response.code() + " - Fetching invitations not successful");
                }
            }

            @Override
            public void onFailure(Call<List<InvitationResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - Fetching invitations failed");
            }
        });
    }

    public void acceptInvitation(int projectId){

    }

    /**
     * shows the projects the user is already member of
     */
    public void getMemberProjects(){

    }

    public static void main(String[] args) {
        WorkspaceRepo w = new WorkspaceRepo();
        //w.createProject("java 2");

        InvitationRequest i = new InvitationRequest("pia@gmail.com",2);
        InvitationRequest i2 = new InvitationRequest("sara@gmail.com",2);
        InvitationRequest[] temp = {i,i2};

        List<String> list = new ArrayList<>();
        list.add("pida@gmail.com");
        list.add("madrie@gmail.com");

        //w.inviteMembers(list,10);

        //w.inviteMembers(temp);

        w.getInvitations();

    }
}
