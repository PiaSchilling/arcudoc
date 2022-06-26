package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.data.service.AccountInformation;
import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.workspace.data.WorkspaceRepo;
import de.hdm_stuttgart.workspace.model.InvitationResponse;
import de.hdm_stuttgart.workspace.model.MemberProjectResponse;
import de.hdm_stuttgart.workspace.model.UserProfile;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import de.hdm_stuttgart.workspace.service.IUserProfile;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkspaceController {

    private final WorkspaceRepo workspaceRepo;
    private final ListProperty<IInvitationResponse> invitationResponsesForUi; //ui class works with IInvitationResponse, Repo works with InvitationResponse
    private final ListProperty<IMemberProjectResponse> memberProjectResponsesForUi;
    private final ObjectProperty<IUserProfile> userProfileForUi;

    @Inject
    public WorkspaceController(WorkspaceRepo workspaceRepo) {
        this.workspaceRepo = workspaceRepo;

        ListProperty<InvitationResponse> invitationResponsesFromRepo = workspaceRepo.getProjectInvitationsProperty(); //map repo response in ui response
        invitationResponsesForUi = new SimpleListProperty<>();
        invitationResponsesFromRepo.addListener((observable, oldValue, newValue) -> updateInvitationsPropertyForUi());

        ListProperty<MemberProjectResponse> memberProjectResponsesFromRepo = workspaceRepo.getMemberProjectsProperty();
        memberProjectResponsesForUi = new SimpleListProperty<>();
        memberProjectResponsesFromRepo.addListener((observable, oldValue, newValue) -> updateMemberProjectsPropertyForUi());

        ObjectProperty<UserProfile> userProfileFromRepo = workspaceRepo.getUserProfileObjectProperty();
        userProfileForUi = new SimpleObjectProperty<>();
        userProfileFromRepo.addListener((observable, oldValue, newValue) -> updateUserProfilePropertyForUi());

    }

    /**
     * wrap the InvitationsResponseProperty received by the workspaceRepo in IInvitationResponseProperty
     * reason for this: retrofit can't instance interface type objects (in repo) and model class InvitationResponse should not be exposed
     */
    private void updateInvitationsPropertyForUi(){
        ObservableList<IInvitationResponse> observableList = FXCollections.observableArrayList();
        observableList.addAll(workspaceRepo.getProjectInvitationsProperty());
        invitationResponsesForUi.setValue(observableList);
    }

    /**
     * wrap the MemberProjectResponseProperty received by the workspaceRepo in IMemberProjectResponseProperty
     * reason for this: retrofit can't instance interface type objects (in repo) and model class MemberProjectResponse should not be exposed
     */
    private void updateMemberProjectsPropertyForUi(){
        ObservableList<IMemberProjectResponse> observableList = FXCollections.observableArrayList();
        observableList.addAll(workspaceRepo.getMemberProjectsProperty());
        memberProjectResponsesForUi.setValue(observableList);
    }

    private void updateUserProfilePropertyForUi(){
       userProfileForUi.setValue(workspaceRepo.getUserProfileObjectProperty().getValue());
    }

    /**
     * call repo to accept a project invitation
     * @param projectId the id of the project for which the invitation should be accepted
     */
    public void acceptProjectInvitation(int projectId){
        workspaceRepo.respondProjectInvitation(projectId,true);
    }

    /**
     * call repo to decline a project invitation
     * @param projectId the id of the project for which the invitation should be declined
     */
    public void declineProjectInvitation(int projectId){
        workspaceRepo.respondProjectInvitation(projectId,false);
    }

    public void reloadProjectsAndInvitations(){
        workspaceRepo.fetchMemberProjects();
        workspaceRepo.fetchProjectInvitations();
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


    public ObjectProperty<IUserProfile> getUserProfileProperty(){
        workspaceRepo.fetchUserProfileAsync();
        return userProfileForUi;
    }

}
