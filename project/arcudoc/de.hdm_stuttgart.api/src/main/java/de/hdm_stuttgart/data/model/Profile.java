package de.hdm_stuttgart.data.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("access_token")
    private  String accessToken;

    @SerializedName("refresh_token")
    private  String refreshToken;

    @SerializedName("expires_in")
    private  long expiresIn;

    private  User user;

    public Profile(){

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

    @Override
    public String toString() {
        return "Profile{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", user=" + user +
                '}';
    }
}
