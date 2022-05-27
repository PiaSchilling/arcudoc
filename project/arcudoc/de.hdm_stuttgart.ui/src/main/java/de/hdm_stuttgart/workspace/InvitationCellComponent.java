package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InvitationCellComponent extends AnchorPane {

    public InvitationCellComponent(IInvitationResponse invitationResponse, IWorkspace workspace) {
        super();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.INVITATION_CELL.getPath()));
            InvitationCellController invitationCellController = new InvitationCellController(invitationResponse, workspace);
            loader.setController(invitationCellController);
            Node node = loader.load();
            this.getChildren().add(node);
        }catch (IOException e){
            e.printStackTrace(); //todo add log
        }
    }
}
