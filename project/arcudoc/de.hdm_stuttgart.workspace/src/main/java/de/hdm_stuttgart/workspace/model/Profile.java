package de.hdm_stuttgart.workspace.model;

public class Profile {

    private String mail;

    public Profile(String mail) {
        this.mail = mail;
    }

    public Profile() {
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "mail='" + mail + '\'' +
                '}';
    }


}
