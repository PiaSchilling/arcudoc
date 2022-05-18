package de.hdm_stuttgart.data.model;

import com.google.gson.annotations.SerializedName;

public class UserMetadata {

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("full_name")
    private String fullName;

    @Override
    public String toString() {
        return "UserMetadata{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
