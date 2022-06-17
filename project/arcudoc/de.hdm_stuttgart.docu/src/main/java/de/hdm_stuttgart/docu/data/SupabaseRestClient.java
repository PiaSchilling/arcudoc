package de.hdm_stuttgart.docu.data;

import de.hdm_stuttgart.docu.model.TemplateResponse;
import de.hdm_stuttgart.docu.service.ITemplateResponse;
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

    @POST("projects")
    Call<Void> setContent(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Query("select") String selectFilter,
            @Query("id") String projectId,
            @Body TemplateResponse templateResponse
    );



}
