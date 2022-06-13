package de.hdm_stuttgart.workspace;

import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InvitationCellComponent extends AnchorPane {

    private final int cellId; //cellId equals projectId of the invitation

    public InvitationCellComponent(IInvitationResponse invitationResponse, WorkspaceCellClickHandler workspaceCellClickHandler) {
        super();

        this.cellId = invitationResponse.getProjectId();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.INVITATION_CELL.getPath()));
            InvitationCellController invitationCellController = new InvitationCellController(invitationResponse, workspaceCellClickHandler);
            loader.setController(invitationCellController);
            Node node = loader.load();
            this.getChildren().add(node);
        }catch (IOException e){
            e.printStackTrace(); //todo add log
        }
    }

    public int getCellId() {
        return cellId;
    }
}
