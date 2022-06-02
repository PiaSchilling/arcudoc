package de.hdm_stuttgart.data.integration;

import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.api.SupabaseAuthClient;
import de.hdm_stuttgart.data.model.Profile;
import de.hdm_stuttgart.data.model.TokenRequest;
import de.hdm_stuttgart.data.service.AccountInformation;
import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.data.service.IProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository implements IProfileRepository {

    private static final Logger log = LogManager.getLogger(ProfileRepository.class);
    private final SupabaseAuthClient authClient = ServiceProvider.getSupabaseAuthClient(); //todo inject with guice

    /**
     * fetch the user profile, profile includes access and refresh tokens
     * @param refreshToken the users refresh token
     */
    @Override
    public void fetchProfile(String refreshToken){
        Call<Profile> call = authClient.getAccesstokenWithRefreshToken(ApiConstants.API_KEY,ApiConstants.BEARER_KEY,new TokenRequest(refreshToken));
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Profile profile = response.body();
                        AccountInformation.getInstance().setAccessToken(profile.getAccessToken());
                        AccountInformation.getInstance().setRefreshToken(profile.getRefreshToken());
                        AccountInformation.getInstance().setExpiresIn(profile.getExpiresIn());
                    }
                }else{
                   log.error("Fetch refresh token not successful - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable throwable) {
                log.error("Fetch refresh token failed - " + throwable.getMessage());
            }
        });
    }
}
