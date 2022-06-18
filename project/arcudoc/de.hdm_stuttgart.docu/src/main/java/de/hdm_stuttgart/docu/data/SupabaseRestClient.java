package de.hdm_stuttgart.docu.data;


import de.hdm_stuttgart.docu.model.TemplateResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface SupabaseRestClient {


    @GET("projects")
    Call<List<TemplateResponse>> getTemplate(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter,
            @Query("id") String projectId
    );

    @PATCH("projects")
    Call<Void> setContent(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Header("Content-Type") String contentType,
            @Header("Prefer") String prefer,
            @Query("id") String projectId,
            @Body TemplateResponse templateResponse
    );



}
