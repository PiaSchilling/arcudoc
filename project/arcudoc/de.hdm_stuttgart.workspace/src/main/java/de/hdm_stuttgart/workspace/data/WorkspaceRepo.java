package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.workspace.model.InvitationRequest;
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
     * calls corresponding api endpoints for creating a new project
     * @param projectTitle the title the project should have
     //* @param emailInvitations list of email-addresses which will be used to invite members to this project
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

    public void addProjectOwner(){

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

        w.inviteMembers(list,10);

        //w.inviteMembers(temp);

    }
}
