package de.hdm_stuttgart.workspace.service;

import de.hdm_stuttgart.workspace.model.InvitationResponse;
import javafx.beans.property.ListProperty;

public interface IWorkspace {

    void onProjectSearchbarClicked();

    String getUserName();

    String getUserMail();

    String getUserAvatarUrl();

    void onCreateProjectClicked();

    ListProperty<IInvitationResponse> getProjectInvitationsProperty();

    void acceptProjectInvitation(int projectId);



}
