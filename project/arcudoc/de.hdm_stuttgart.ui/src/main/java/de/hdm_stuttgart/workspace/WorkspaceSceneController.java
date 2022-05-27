package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class WorkspaceSceneController {

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


    public void initialize() {
        //fetch all projects
        //fetch all invitations
        //add navigation for create project

        workspace.getProjectInvitationsProperty().addListener((observable, oldValue, newValue) -> {
            for(IInvitationResponse r : newValue){
                System.out.println(r.getMemberMail());
                System.out.println(r.getProjectTitle());
            }
        });



        projectsSearchbar.setOnAction(event -> onProjectSearchBarClicked());
        createProjectButton.setOnMouseClicked(event -> onCreateProjectClicked());

        //todo add buttons

        userNameLabel.setText(workspace.getUserName());
        userMailLabel.setText(workspace.getUserMail());
        //todo add avatar


    }

    private void onProjectSearchBarClicked(){
        workspace.onProjectSearchbarClicked();
        System.out.println("Searchbar clicked");
    }


    private void onCreateProjectClicked(){
        workspace.onCreateProjectClicked();
    }



}
