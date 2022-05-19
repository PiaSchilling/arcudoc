package de.hdm_stuttgart.workspace.service;

public interface IWorkspace {

    void onProjectSearchbarClicked();

    String getUserName();

    String getUserMail();

    String getUserAvatarUrl();

    void onCreateProjectClicked();

    void onJoinProjectClicked();

}
