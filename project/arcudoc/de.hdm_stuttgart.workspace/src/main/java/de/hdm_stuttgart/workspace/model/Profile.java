package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    private String mail;

    @SerializedName("display_name")
    private String userName;

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

    @Override
    public String toString() {
        return "Profile{" +
                "mail='" + mail + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
