package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.workspace.service.IUserProfile;

public class UserProfile implements IUserProfile {

    private String mail;

    private String avatar;

    @SerializedName("display_name")
    private String userName;

    public UserProfile(String mail, String avatar, String userName) {
        this.mail = mail;
        this.avatar = avatar;
        this.userName = userName;
    }

    public UserProfile() {
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "mail='" + mail + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getHelloText(){
        String firstName = userName.split(" ")[0];
        return "Hi, " + firstName + "!";
    }
}
