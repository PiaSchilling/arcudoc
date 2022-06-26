package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    private String mail;

    @SerializedName("display_name")
    private String userName;

    @SerializedName("avatar")
    private String avatarUrl;

    public Profile(String mail) {
        this.mail = mail;
    }

    public Profile() {
    }

    public String getMail() {
        return mail;
    }

    public String getUserName(){
        return userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "mail='" + mail + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }


}
