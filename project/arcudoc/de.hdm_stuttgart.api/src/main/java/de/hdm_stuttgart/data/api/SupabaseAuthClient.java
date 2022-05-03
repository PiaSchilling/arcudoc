package de.hdm_stuttgart.data.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SupabaseAuthClient {

    @POST("token?grant_type=refresh_token")
    Call<String> getAccesstokenWithRefreshToken(
            @Header("apikey") String apikey,
            @Body String refreshToken
    );
}
