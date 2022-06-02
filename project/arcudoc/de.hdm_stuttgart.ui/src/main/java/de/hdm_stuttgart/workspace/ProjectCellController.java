package de.hdm_stuttgart.workspace;

import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class ProjectCellController {

    private IMemberProjectResponse memberProject;

    public ProjectCellController(IMemberProjectResponse memberProject) {
        this.memberProject = memberProject;
    }

    @FXML
    private Label lastUpdatedLabel;

    @FXML
    private Circle projectAvatarFrame;

    @FXML
    private Label projectOwnerLabel;

    @FXML
    private Label projectRoleLabel;

    @FXML
    private Label projectTitleLabel;

    public void initialize(){
        lastUpdatedLabel.setText(memberProject.getLastUpdated());
        projectOwnerLabel.setText(memberProject.getOwnerMail());
        projectRoleLabel.setText(memberProject.getProjectRole());
        projectTitleLabel.setText(memberProject.getProjectTitle());
    }
}
