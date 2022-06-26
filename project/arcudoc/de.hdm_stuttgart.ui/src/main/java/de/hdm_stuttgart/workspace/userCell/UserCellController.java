package de.hdm_stuttgart.workspace.userCell;

import de.hdm_stuttgart.workspace.CreateProjectCellClickHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class UserCellController {

    private final String memberMail;
    private final CreateProjectCellClickHandler cellClickHandler;

    @FXML
    private Text memberMailLabel;

    @FXML
    private Button removeUserButton;

    public UserCellController(String memberMail, CreateProjectCellClickHandler clickHandler){
        System.out.println(memberMail);
        this.memberMail = memberMail;
        this.cellClickHandler = clickHandler;
    }

    public void initialize(){
        memberMailLabel.setText(memberMail);
        removeUserButton.setOnAction(event -> cellClickHandler.onRemoveUserClicked());
    }
}
