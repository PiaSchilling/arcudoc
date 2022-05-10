package de.hdm_stuttgart.data.model;

import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.api.SupabaseAuthClient;

import static de.hdm_stuttgart.data.service.ApiConstants.apiKey;

public class Profile {

    @SerializedName("access_token")
    private  String accessToken;

    @SerializedName("refresh_token")
    private  String refreshToken;

    @SerializedName("expires_in")
    private  long expiresIn;

    private  User user;

    private  long tokenTimestamp;
    private boolean expired;

    public Profile(){

    }

    public boolean isExpired() {
        expired = tokenTimestamp + expiresIn < System.currentTimeMillis();
        return expired;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public User getUser() {
        return user;
    }

    public long getTokenTimestamp() {
        return tokenTimestamp;
    }
}
