package de.hdm_stuttgart.workspace;

import de.hdm_stuttgart.navigation.NavigationController;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.w3c.dom.ls.LSOutput;

public class ProjectCellController {

    private final IMemberProjectResponse memberProject;

    public ProjectCellController(IMemberProjectResponse memberProject) {
        this.memberProject = memberProject;
    }

    @FXML
    private Label lastUpdatedLabel;

    @FXML
    private Label projectOwnerLabel;

    @FXML
    private Label projectRoleLabel;

    @FXML
    private Label projectJobLabel;

    @FXML
    private Label projectTitleLabel;

    @FXML
    private HBox projectCellFrame;

    @FXML
    private Label projectInitialLabel;


    public void initialize(){
        lastUpdatedLabel.setText(memberProject.getLastUpdated());
        projectOwnerLabel.setText(memberProject.getOwnerName());
        projectRoleLabel.setText(memberProject.getProjectRole());
        projectJobLabel.setText(memberProject.getJobLabel());
        projectTitleLabel.setText(memberProject.getProjectTitle());
        projectInitialLabel.setText(memberProject.getProjectTitle().substring(0,1).toUpperCase());
        projectCellFrame.setOnMouseClicked(event -> {
            NavigationController.getINSTANCE()
                    .showProjectScene(memberProject.getProjectId());
        });

        projectOwnerLabel.setTooltip(new OwnerCellComponent(memberProject));

    }


}
