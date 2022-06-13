package de.hdm_stuttgart.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * required for sending json body with retrofit
 */
public class TokenRequest {

    @SerializedName("refresh_token")
    private final String refreshToken;

    public TokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
