package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.data.WorkspaceRepo;
import de.hdm_stuttgart.workspace.model.InvitationResponse;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkspaceController {

    private final WorkspaceRepo workspaceRepo;
    private final ListProperty<IInvitationResponse> invitationResponsesForUi; //ui class works with IInvitationResponse, Repo works with InvitationResponse

    @Inject
    public WorkspaceController(WorkspaceRepo workspaceRepo) {
        this.workspaceRepo = workspaceRepo;

        ListProperty<InvitationResponse> invitationResponsesFromRepo = workspaceRepo.getProjectInvitationsProperty(); //map repo response in ui reponse
        invitationResponsesForUi = new SimpleListProperty<>();
        invitationResponsesFromRepo.addListener((observable, oldValue, newValue) -> updatePropertyForUi());
    }

    private void updatePropertyForUi(){
        ObservableList<IInvitationResponse> observableList = FXCollections.observableArrayList();
        observableList.addAll(workspaceRepo.getProjectInvitationsProperty());
        invitationResponsesForUi.setValue(observableList);
    }


    public ListProperty<IInvitationResponse> getProjectInvitationsProperty() {
        workspaceRepo.fetchProjectInvitations();
        return invitationResponsesForUi;
    }
}
