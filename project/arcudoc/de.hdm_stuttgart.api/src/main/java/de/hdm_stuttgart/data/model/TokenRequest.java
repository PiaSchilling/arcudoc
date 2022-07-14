package de.hdm_stuttgart.data.model;

/**
 * required for sending json body with retrofit
 */
public class TokenRequest {

    private final String refreshToken;

    public TokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
