package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.workspace.model.InvitationRequest;
import de.hdm_stuttgart.workspace.model.ProjectRequest;
import de.hdm_stuttgart.workspace.model.ProjectResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.List;

public interface SupabaseRestClient {

    @POST("projects")
    Call<List<ProjectResponse>> createNewProject(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String returnRepresentation,
            @Body ProjectRequest projectRequest
            );

    @POST("project-invitations")
    Call<Void> createNewProjectInvitation(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Body List<InvitationRequest> invitationRequests
            );
}
