package de.hdm_stuttgart.data.service;

import de.hdm_stuttgart.data.model.HtmlResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GitLabClient {

    @POST("markdown")
    Call<HtmlResponse> fetchMarkDown(
            @Query("text") String textToRender);

}
