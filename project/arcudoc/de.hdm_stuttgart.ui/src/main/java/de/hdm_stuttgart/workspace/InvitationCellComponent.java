package de.hdm_stuttgart.workspace;

import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InvitationCellComponent extends AnchorPane {

    private final int cellId;

    public InvitationCellComponent(IInvitationResponse invitationResponse, IWorkspace workspace, CellClickHandler cellClickHandler) {
        super();

        this.cellId = invitationResponse.getProjectId();

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.INVITATION_CELL.getPath()));
            InvitationCellController invitationCellController = new InvitationCellController(invitationResponse, workspace, cellClickHandler);
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
