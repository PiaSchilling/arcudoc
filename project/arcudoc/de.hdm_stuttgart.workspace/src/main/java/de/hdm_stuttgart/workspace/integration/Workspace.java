package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.beans.property.ListProperty;

public class Workspace implements IWorkspace {

    private final WorkspaceController workspaceController;

    @Inject
    public Workspace(WorkspaceController workspaceController) {
        this.workspaceController = workspaceController;
    }

    @Override
    public void onProjectSearchbarClicked() {

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getUserMail() {
        return null;
    }

    @Override
    public String getUserAvatarUrl() {
        return null;
    }


    @Override
    public void onCreateProjectClicked() {

    }

    public ListProperty<IInvitationResponse> getProjectInvitationsProperty() {
        return workspaceController.getProjectInvitationsProperty();
    }

    @Override
    public void acceptProjectInvitation(int projectId) {
        workspaceController.acceptProjectInvitation(projectId);
    }
}
