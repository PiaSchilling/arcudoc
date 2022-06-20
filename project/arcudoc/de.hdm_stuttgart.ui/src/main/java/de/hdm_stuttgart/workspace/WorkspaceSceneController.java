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

import java.util.Optional;

public class WorkspaceSceneController implements WorkspaceCellClickHandler {

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
    private ScrollPane projectScrollPane;

    @FXML
    private VBox invitationCellVBox; //todo auto compute height of box

    @FXML
    private VBox projectCellVBox; //todo auto compute height of box


    public void initialize() {

        //show the users project invitations
        workspace.getProjectInvitationsProperty().addListener((observable, oldValue, newValue) -> {

            Platform.runLater(() -> setInvitationContainerHeight(newValue.size()));

            for(IInvitationResponse invitation : newValue){
                InvitationCellComponent invitationCell = new InvitationCellComponent(invitation,this);
                Platform.runLater(() -> invitationCellVBox.getChildren().add(invitationCell));
            }
        });

        //show the users projects he is already a part of
        workspace.getMemberProjectsProperty().addListener((observable, oldValue, newValue) -> {

            Platform.runLater(() -> {
                projectCellVBox.getChildren().clear();
                setProjectContainerHeight(newValue.size());
            }); //todo can be solved better by filtering the list

            for(IMemberProjectResponse memberProject : newValue){
                ProjectCellComponent projectCell = new ProjectCellComponent(memberProject);

                Platform.runLater(() -> projectCellVBox.getChildren().add(projectCell));

            }
        });

        projectsSearchbar.setOnAction(event -> {});//todo implement searchbar
        createProjectButton.setOnMouseClicked(event -> NavigationController.getINSTANCE().showCreateProjectScene());
        projectInvitationsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        projectInvitationsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        projectScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        projectScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        /*
        userNameLabel.setText(workspace.getUserName());
        userMailLabel.setText(workspace.getUserMail());
*/
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

    /**
     * auto compute the height of the vbox to enable scrolling if many cells are added
     * @param projectsCount the number of cells going to be displayed in the box
     */
    private void setProjectContainerHeight(int projectsCount){
        double height = projectsCount * 175;
        this.projectCellVBox.setPrefHeight(height);
    }

    /**
     * auto compute the height of the vbox to enable scrolling if many cells are added
     * @param invitationsCount the number of cells going to be displayed in the box
     */
    private void setInvitationContainerHeight(int invitationsCount){
        double height = invitationsCount * 175;
        this.invitationCellVBox.setPrefHeight(height); //todo add spacing between cells
    }
}
