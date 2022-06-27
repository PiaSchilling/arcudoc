package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import de.hdm_stuttgart.workspace.service.IUserProfile;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;

public class Workspace implements IWorkspace {

    private final WorkspaceController workspaceController;

    @Inject
    public Workspace(WorkspaceController workspaceController) {
        this.workspaceController = workspaceController;
    }


    public ListProperty<IInvitationResponse> getProjectInvitationsProperty() {
        return workspaceController.getProjectInvitationsProperty();
    }

    public ListProperty<IMemberProjectResponse> getMemberProjectsProperty(){
        return workspaceController.getMemberProjectProperty();
    }

    @Override
    public ObjectProperty<NetworkStatus> getNetworkStatusProperty() {
        return workspaceController.getNetworkStatusProperty();
    }

    @Override
    public ObjectProperty<IUserProfile> getUserProfileProperty(){
        return workspaceController.getUserProfileProperty();
    }

    @Override
    public void acceptProjectInvitationClicked(int projectId) {
        workspaceController.acceptProjectInvitation(projectId);
    }

    @Override
    public void declineProjectInvitationClicked(int projectId) {
        workspaceController.declineProjectInvitation(projectId);
    }

    @Override
    public void refreshButtonClicked() {
        workspaceController.reloadProjectsAndInvitations();
    }
}
