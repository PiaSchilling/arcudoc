package de.hdm_stuttgart.workspace.data;

import de.hdm_stuttgart.workspace.model.ProjectRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SupabaseRestClient {

    @POST("projects")
    Call<Void> createNewProject(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String returnRepresentation,
            @Body ProjectRequestModel projectRequest
            );
}
