package de.hdm_stuttgart.workspace.service;

import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.workspace.model.UserProfile;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;

public interface IWorkspace {

    void onProjectSearchbarClicked();

    void onCreateProjectClicked();

    ListProperty<IInvitationResponse> getProjectInvitationsProperty();

    ListProperty<IMemberProjectResponse> getMemberProjectsProperty();

    ObjectProperty<NetworkStatus> getNetworkStatusProperty();

    ObjectProperty<IUserProfile> getUserProfileProperty();

    void acceptProjectInvitationClicked(int projectId);

    void declineProjectInvitationClicked(int projectId);

    void refreshButtonClicked();

}
