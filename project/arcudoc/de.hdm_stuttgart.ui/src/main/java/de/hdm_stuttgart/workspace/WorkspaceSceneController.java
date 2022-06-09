package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.navigation.NavigationController;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkspaceSceneController implements CellClickHandler {

    private final IWorkspace workspace;


    @Inject
    public WorkspaceSceneController(IWorkspace workspace) {
        this.workspace = workspace;
    }

    @FXML
    private HBox createProjectButton;

    @FXML
    private HBox joinProjectButton;

    @FXML
    private TextField projectsSearchbar;

    @FXML
    private Circle userAvatar;

    @FXML
    private Label userMailLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Rectangle rect;

    @FXML
    private ScrollPane projectInvitationsScrollPane;

    @FXML
    private VBox invitationCellVBox; //todo auto compute height of box

    @FXML
    private VBox projectCellVBox; //todo auto compute height of box


    public void initialize() {

        //show the users project invitations
        workspace.getProjectInvitationsProperty().addListener((observable, oldValue, newValue) -> {
            for(IInvitationResponse invitation : newValue){
                InvitationCellComponent invitationCell = new InvitationCellComponent(invitation,this);
                Platform.runLater(() -> invitationCellVBox.getChildren().add(invitationCell));
            }
        });

        //show the users projects he is already a part of
        workspace.getMemberProjectsProperty().addListener((observable, oldValue, newValue) -> {

            if(oldValue != null){
                newValue.removeAll(oldValue);
            }

            for(IMemberProjectResponse memberProject : newValue){
                ProjectCellComponent projectCell = new ProjectCellComponent(memberProject); //todo prevent duplicate adding of nodes

                Platform.runLater(() -> projectCellVBox.getChildren().add(projectCell));

            }
        });

        projectsSearchbar.setOnAction(event -> {});//todo implement searchbar
        createProjectButton.setOnMouseClicked(event -> NavigationController.getINSTANCE().showCreateProjectScene());

        userNameLabel.setText(workspace.getUserName());
        userMailLabel.setText(workspace.getUserMail());

    }


    /**
     * defines action for click on "accept invitation" on an invitation cell
     * (trigger accept invitation action and delete corresponding ui component (cell))
     * @param projectId the id of the project for which the invitation should be accepted
     */
    @Override
    public void onAcceptInvitationClicked(int projectId) {
        workspace.acceptProjectInvitation(projectId);
        Optional<InvitationCellComponent> componentOptional = this.invitationCellVBox.getChildren()
                .stream()
                .map(node -> (InvitationCellComponent) node)
                .filter(invitationCellComponent -> invitationCellComponent.getCellId() == projectId)
                .findAny();
        componentOptional.ifPresent(invitationCellComponent -> invitationCellVBox.getChildren().removeAll(invitationCellComponent));
    }
}