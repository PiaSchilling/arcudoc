package de.hdm_stuttgart.docu;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.service.IDocu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DocuController {

    private final IDocu docuLogic;


    @FXML
    private ImageView logo;

    @FXML
    private Label chapterAufgabenstellung;


    @Inject
    public DocuController (IDocu docuLogic){
        this.docuLogic = docuLogic;
    }


    public void initialize(){
        System.out.println("init called");
        // fxmlId.actionMethod (event -> methodDoThings());
        logo.setOnMouseClicked(event -> onLogoCLicked());
        chapterAufgabenstellung.setOnMouseClicked(event -> onAufgabenstellungClicked());
    }

    private void onLogoCLicked(){
        docuLogic.onLogoCLicked();
        System.out.println("Logo clicked");
    }

    private void onAufgabenstellungClicked(){
        docuLogic.onAufgabenstellungClicked();
        System.out.println("01.1 Aufgabenstellung clicked");
    }
}
