package de.hdm_stuttgart.workspace.userCell;

import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.workspace.CreateProjectCellClickHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserCellComponent extends AnchorPane {

    public UserCellComponent(String memberMail, CreateProjectCellClickHandler cellClickHandler) {
        super();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.USER_CELL.getPath()));
            System.out.println(memberMail);
            UserCellController userCellController = new UserCellController(memberMail, cellClickHandler);
            loader.setController(userCellController);
            Node node = loader.load();
            this.getChildren().add(node);
        }catch (IOException e){
            e.printStackTrace(); //todo add log
        }
    }
}
