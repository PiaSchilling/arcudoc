package de.hdm_stuttgart.data.api;

import de.hdm_stuttgart.data.model.Profile;
import de.hdm_stuttgart.data.model.TokenRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SupabaseAuthClient {

    @POST("token?grant_type=refresh_token")
    Call<Profile> getAccessTokenWithRefreshToken(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            //@Query("grant_type") String grantType,
            @Body TokenRequest tokenRequest
    );
}
