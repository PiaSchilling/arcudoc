package de.hdm_stuttgart.data.model;

import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.api.SupabaseAuthClient;

import static de.hdm_stuttgart.data.service.ApiConstants.apiKey;

public class Profile {

    private final String accessToken;
    private final String refreshToken;
    private final long expiresIn;
    private final long tokenTimestamp;
    private boolean expired;

    private final SupabaseAuthClient authClient = ServiceProvider.getSupabaseAuthClient();

    public Profile(String accessToken, String refreshToken, long expiresIn, long tokenTimestamp) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.tokenTimestamp = tokenTimestamp;
    }

    private boolean isExpired() {
        expired = tokenTimestamp + expiresIn < System.currentTimeMillis();
        return expired;
    }

    public String getAccessToken() {
        if(isExpired()){
            authClient.getAccesstokenWithRefreshToken(apiKey,refreshToken);
            return null; //todo implement action when token is expired (fetch new token)
        }else{
            return accessToken;
        }
    }
}
