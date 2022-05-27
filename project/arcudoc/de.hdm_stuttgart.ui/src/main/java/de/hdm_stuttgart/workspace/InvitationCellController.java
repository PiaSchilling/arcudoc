package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class InvitationCellController {

    private final IInvitationResponse invitationResponse;
    private final IWorkspace workspace;

    @Inject
    public InvitationCellController(IInvitationResponse invitationResponse, IWorkspace workspace) {
        this.invitationResponse = invitationResponse;
        this.workspace = workspace;
    }

    @FXML
    private ImageView acceptButton;

    @FXML
    private ImageView declineButton;

    @FXML
    private Label projectNameLabel;

    @FXML
    private Label projectRoleLabel;

    public void initialize(){
        projectNameLabel.setText(invitationResponse.getProjectTitle());
        projectRoleLabel.setText(invitationResponse.getProjectRole());

        acceptButton.setOnMouseClicked(event -> workspace.acceptProjectInvitation(invitationResponse.getProjectId()));
    }
}
