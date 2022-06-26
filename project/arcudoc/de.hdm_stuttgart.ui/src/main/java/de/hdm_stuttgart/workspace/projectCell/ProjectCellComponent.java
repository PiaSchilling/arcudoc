package de.hdm_stuttgart.workspace.projectCell;

import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProjectCellComponent extends AnchorPane {

    public ProjectCellComponent(IMemberProjectResponse memberProject){
        super();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.PROJECT_CELL.getPath()));
            ProjectCellController projectCellController = new ProjectCellController(memberProject);
            loader.setController(projectCellController);
            Node node = loader.load();
            this.getChildren().add(node);
        }catch (IOException e){
            e.printStackTrace(); //todo add log
        }
    }
}
