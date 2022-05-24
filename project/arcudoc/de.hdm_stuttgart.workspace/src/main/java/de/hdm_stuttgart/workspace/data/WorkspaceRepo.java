package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.workspace.model.ProjectRequestModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class WorkspaceRepo {

    SupabaseRestClient supabaseRestClient = ServiceProvider.getSupabaseRestClient();
    /**
     * calls corresponding api endpoints for creating a new project
     * @param projectTitle the title the project should have
     //* @param emailInvitations list of email-addresses which will be used to invite members to this project
     */
    public void createProject(String projectTitle){

        Call<Void> call = supabaseRestClient.createNewProject(
                ApiConstants.API_KEY,
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNjUzNDAzNzAzLCJzdWIiOiJmNjIwNmY0Ni0wYzc4LTQ3NjQtYjhmNC1lZjIwNWQyZTNlOTIiLCJlbWFpbCI6Im5hY2h2b3JuMTNAZ21haWwuY29tIiwicGhvbmUiOiIiLCJhcHBfbWV0YWRhdGEiOnsicHJvdmlkZXIiOiJnaXRsYWIiLCJwcm92aWRlcnMiOlsiZ2l0bGFiIl19LCJ1c2VyX21ldGFkYXRhIjp7ImF2YXRhcl91cmwiOiJodHRwczovL3NlY3VyZS5ncmF2YXRhci5jb20vYXZhdGFyLzM5YTc1YmM1OTBlZjk0Y2U3MzIwNjFhNzkxMDcxZWZhP3M9ODBcdTAwMjZkPWlkZW50aWNvbiIsImVtYWlsIjoibmFjaHZvcm4xM0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZnVsbF9uYW1lIjoiTWFyaWUgU2NoaWxsaW5nIiwiaXNzIjoiaHR0cHM6Ly9naXRsYWIuY29tIiwibmFtZSI6Ik1hcmllIFNjaGlsbGluZyIsInBpY3R1cmUiOiJodHRwczovL3NlY3VyZS5ncmF2YXRhci5jb20vYXZhdGFyLzM5YTc1YmM1OTBlZjk0Y2U3MzIwNjFhNzkxMDcxZWZhP3M9ODBcdTAwMjZkPWlkZW50aWNvbiIsInByb3ZpZGVyX2lkIjoiMTE2NTE1MjAiLCJzdWIiOiIxMTY1MTUyMCJ9LCJyb2xlIjoiYXV0aGVudGljYXRlZCJ9.maAT_npFAtRDtoh9YFL3F-NVZetMYxfyiwL0J4OO-4w",
                "application/json",
                "return=representation",
                new ProjectRequestModel(projectTitle));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    System.out.println("SUCCESS");
                }else{
                    System.out.println("ERROR");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                System.out.println("FAIL");
            }
        });

        //insert into projects
        //insert owner into project members
        //insert owner into members //todo move to login
        //insert emails into invitations
    }

    public static void main(String[] args) {
        WorkspaceRepo w = new WorkspaceRepo();
        w.createProject("java");
    }
}
