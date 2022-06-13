package de.hdm_stuttgart.docu.data;

import de.hdm_stuttgart.docu.model.TemplateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import java.util.List;

public interface SupabaseRestClient {


    @GET("projects")
    Call<List<TemplateResponse>> getTemplate(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter,
            @Query("id") String projectId
    );
}
