package de.hdm_stuttgart.data.api;

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

import java.util.prefs.Preferences;

public class ProfileRepository implements IProfileRepository {

    private static final Logger log = LogManager.getLogger(ProfileRepository.class);
    private final SupabaseAuthClient authClient = ServiceProvider.getSupabaseAuthClient(); //todo inject with guice

    /**
     * fetch the user profile, profile includes access and refresh tokens
     */
    @Override
    public void fetchProfile(){

        String refreshToken = getRefreshTokenFromPreferences();

        Call<Profile> call = authClient.getAccessTokenWithRefreshToken(ApiConstants.API_KEY,ApiConstants.BEARER_KEY,new TokenRequest(refreshToken));
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Profile profile = response.body();
                        AccountInformation.getInstance().setAccessToken(profile.getAccessToken());
                        AccountInformation.getInstance().setRefreshToken(profile.getRefreshToken());
                        AccountInformation.getInstance().setExpiresIn(profile.getExpiresIn());

                        setRefreshTokenToPreferences(profile.getRefreshToken());
                        log.debug("Profile information fetched successfully");
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


    /**
     * writes the refreshToken to userPreferences to persistently store it
     * @param refreshToken the token to store
     */
    public void setRefreshTokenToPreferences(String refreshToken) {
        Preferences userPreferences = Preferences.userRoot().node(this.getClass().getName());
        userPreferences.put("REFRESH_TOKEN",refreshToken);
        log.debug("Set refresh token to user preferences");
    }

    /**
     * gets the refreshToken from userPreferences
     * @return the refreshToken as a String
     */
    public String getRefreshTokenFromPreferences(){
        Preferences userPreferences = Preferences.userRoot().node(this.getClass().getName());
        return userPreferences.get("REFRESH_TOKEN","default");
    }

}
