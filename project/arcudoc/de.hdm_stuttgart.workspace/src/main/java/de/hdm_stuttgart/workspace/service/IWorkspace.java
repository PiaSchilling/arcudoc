package de.hdm_stuttgart.workspace.service;

import de.hdm_stuttgart.data.service.NetworkStatus;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;

public interface IWorkspace {

    void onProjectSearchbarClicked();

    String getUserName();

    String getUserMail();

    String getUserAvatarUrl();

    void onCreateProjectClicked();

    ListProperty<IInvitationResponse> getProjectInvitationsProperty();

    ListProperty<IMemberProjectResponse> getMemberProjectsProperty();

    ObjectProperty<NetworkStatus> getNetworkStatusProperty();

    void acceptProjectInvitation(int projectId);

    void declineProjectInvitation(int projectId);

}
