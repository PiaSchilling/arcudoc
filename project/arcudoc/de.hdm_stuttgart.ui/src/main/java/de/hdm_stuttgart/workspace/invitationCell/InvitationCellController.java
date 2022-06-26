package de.hdm_stuttgart.workspace.invitationCell;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.WorkspaceCellClickHandler;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class InvitationCellController {

    private final IInvitationResponse invitationResponse;
    private final WorkspaceCellClickHandler cellClickHandler; // "parent" scene which is responsible for handling actions

    @Inject
    public InvitationCellController(IInvitationResponse invitationResponse, WorkspaceCellClickHandler cellClickHandler) {
        this.invitationResponse = invitationResponse;
        this.cellClickHandler = cellClickHandler;
    }

    @FXML
    private ImageView acceptButton;

    @FXML
    private ImageView declineButton;

    @FXML
    private Label projectNameLabel;

    @FXML
    private Label projectRoleLabel;

    @FXML
    private Label projectJobLabel;

    public void initialize(){
        projectNameLabel.setText(invitationResponse.getProjectTitle());
        projectJobLabel.setText(invitationResponse.getJobLabel());
        projectRoleLabel.setText( invitationResponse.getProjectRole());

        acceptButton.setOnMouseClicked(event -> cellClickHandler.onAcceptInvitationClicked(invitationResponse.getProjectId()));

        declineButton.setOnMouseClicked(event -> cellClickHandler.onDeclineInvitationClicked(invitationResponse.getProjectId()));

        //todo decline invitation

        //todo delete node when accepted/declined

    }
}
