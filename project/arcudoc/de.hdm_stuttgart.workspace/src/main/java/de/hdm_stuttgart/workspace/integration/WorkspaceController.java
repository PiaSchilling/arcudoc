package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.workspace.data.WorkspaceRepo;
import de.hdm_stuttgart.workspace.model.InvitationResponse;
import de.hdm_stuttgart.workspace.model.MemberProjectResponse;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkspaceController {

    private final WorkspaceRepo workspaceRepo;
    private final ListProperty<IInvitationResponse> invitationResponsesForUi; //ui class works with IInvitationResponse, Repo works with InvitationResponse
    private final ListProperty<IMemberProjectResponse> memberProjectResponsesForUi;

    @Inject
    public WorkspaceController(WorkspaceRepo workspaceRepo) {
        this.workspaceRepo = workspaceRepo;

        ListProperty<InvitationResponse> invitationResponsesFromRepo = workspaceRepo.getProjectInvitationsProperty(); //map repo response in ui reponse
        invitationResponsesForUi = new SimpleListProperty<>();
        invitationResponsesFromRepo.addListener((observable, oldValue, newValue) -> updateInvitationsPropertyForUi());

        ListProperty<MemberProjectResponse> memberProjectResponsesFromRepo = workspaceRepo.getMemberProjectsProperty();
        memberProjectResponsesForUi = new SimpleListProperty<>();
        memberProjectResponsesFromRepo.addListener((observable, oldValue, newValue) -> updateMemberProjectsPropertyForUi());
    }

    private void updateInvitationsPropertyForUi(){
        ObservableList<IInvitationResponse> observableList = FXCollections.observableArrayList();
        observableList.addAll(workspaceRepo.getProjectInvitationsProperty());
        invitationResponsesForUi.setValue(observableList);
    }

    private void updateMemberProjectsPropertyForUi(){
        ObservableList<IMemberProjectResponse> observableList = FXCollections.observableArrayList();
        observableList.addAll(workspaceRepo.getMemberProjectsProperty());
        memberProjectResponsesForUi.setValue(observableList);
    }

    public void acceptProjectInvitation(int projectId){
        workspaceRepo.respondProjectInvitation(projectId,true);
    }

    public void declineProjectInvitation(int projectId){
        workspaceRepo.respondProjectInvitation(projectId,false);
    }

    // - - - -  get observable properties - - - -

    public ListProperty<IInvitationResponse> getProjectInvitationsProperty() {
        workspaceRepo.fetchProjectInvitations();
        return invitationResponsesForUi;
    }

    public ListProperty<IMemberProjectResponse> getMemberProjectProperty(){
        workspaceRepo.fetchMemberProjects();
        return memberProjectResponsesForUi;
    }



    public ObjectProperty<NetworkStatus> getNetworkStatusProperty(){
        return workspaceRepo.getNetworkStatusObjectProperty();
    }
}
