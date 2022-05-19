package de.hdm_stuttgart.data.api;

import de.hdm_stuttgart.data.model.Profile;
import de.hdm_stuttgart.data.model.TokenRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SupabaseAuthClient {

    @POST("token?grant_type=refresh_token")
    Call<Profile> getAccesstokenWithRefreshToken(
            @Header("apikey") String apikey,
            @Header("Authorization") String bearerToken,
            @Body TokenRequest tokenRequest
    );
}
