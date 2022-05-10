package de.hdm_stuttgart.data.api;

import de.hdm_stuttgart.data.model.Profile;
import de.hdm_stuttgart.data.model.TokenRequest;
import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.data.service.IProfileRepository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository implements IProfileRepository {

    private final SupabaseAuthClient authClient = ServiceProvider.getSupabaseAuthClient(); //todo inject with guice
    private Profile profile;

    public ProfileRepository() {
        //this.authClient = authClient;
    }

    @Override
    public void fetchAccessToken(String refreshToken){
        Call<Profile> call = authClient.getAccesstokenWithRefreshToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRrdWx2aXZhbHRveXlndWl2cnNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NDg0OTk4NjgsImV4cCI6MTk2NDA3NTg2OH0.smUOZMVWkh6GoxYDuxM6Y8pkxySBUYz3ZxJTSihAY5o",ApiConstants.apiKey,new TokenRequest(refreshToken));
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        profile = response.body();
                        System.out.println(profile.getAccessToken());
                        System.out.println(response.body());
                    }
                }else{
                    System.out.println("Call not successful " + response.message());
                    System.out.println(response.code());
                    System.out.println(response.errorBody());
                    System.out.println(response.raw());
                    System.out.println(response.body());
                    System.out.println(response.headers());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }

    @Override
    public Profile getProfile() {
       return this.profile;
    }

    public static void main(String[] args) {
        ProfileRepository p = new ProfileRepository();
        String refresh = "Z9knDB0UBISB-F19wXJAUw"; //xRFiw052kntzKG_Z3nHmYA

        System.out.println(refresh);
        p.fetchAccessToken(refresh);
    }


}
