package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class WorkspaceController {

    private final IWorkspace workspaceLogic;

    @Inject
    public WorkspaceController(IWorkspace workspaceLogic) {
        this.workspaceLogic = workspaceLogic;
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
        System.out.println("Init called");

        projectsSearchbar.setOnAction(event -> onProjectSearchBarClicked());
        joinProjectButton.setOnMouseClicked(event -> onJoinProjectClicked());
        createProjectButton.setOnMouseClicked(event -> onCreateProjectClicked());

        //todo add buttons

        userNameLabel.setText(workspaceLogic.getUserName());
        userMailLabel.setText(workspaceLogic.getUserMail());
        //todo add avatar


    }

    private void onProjectSearchBarClicked(){
        workspaceLogic.onProjectSearchbarClicked();
        System.out.println("Searchbar clicked");
    }

    private void onJoinProjectClicked(){
        workspaceLogic.onJoinProjectClicked();
        System.out.println("Join project clicked");
    }

    private void onCreateProjectClicked(){
        workspaceLogic.onCreateProjectClicked();
    }



}
