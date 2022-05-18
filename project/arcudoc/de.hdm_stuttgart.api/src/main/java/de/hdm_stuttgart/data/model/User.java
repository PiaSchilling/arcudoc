package de.hdm_stuttgart.data.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String userId;

    private String email;

    @SerializedName("user_metadata")
    private UserMetadata metadata;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
