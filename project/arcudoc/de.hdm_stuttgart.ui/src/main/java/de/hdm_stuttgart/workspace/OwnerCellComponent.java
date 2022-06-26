package de.hdm_stuttgart.workspace;

import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import java.io.IOException;

public class OwnerCellComponent extends Tooltip {

    public OwnerCellComponent(IMemberProjectResponse projectResponse) {
        super();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.OWNER_CELL.getPath()));
            OwnerCellController ownerCellController = new OwnerCellController(projectResponse);
            loader.setController(ownerCellController);
            Node node = loader.load();
            this.setGraphic(node);
            this.setStyle("-fx-background-insets: 30;");
        }catch (IOException e){
            e.printStackTrace(); //todo add log
        }
    }
}
