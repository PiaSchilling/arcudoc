package de.hdm_stuttgart.project;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.integration.EditorState;
import de.hdm_stuttgart.editor.service.IEditor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class ProjectSceneController {

    private IEditor editor;

    @Inject
    public ProjectSceneController(IEditor editor){
        this.editor = editor;
    }

    @FXML
    private Button editButton;

    @FXML
    private AnchorPane anchorPane; //todo rename

    private TextArea textArea;
    private WebView webView;

    public void initialize() {
        editButton.setOnAction(event -> onEditButtonClicked());

        anchorPane.setStyle("-fx-background-color: green");

        textArea = new TextArea();
        textArea.setMaxWidth(anchorPane.getMaxWidth());
        AnchorPane.setTopAnchor(textArea,0.0);
        AnchorPane.setBottomAnchor(textArea,0.0);
        AnchorPane.setLeftAnchor(textArea,0.0);
        AnchorPane.setRightAnchor(textArea,0.0);

        webView = new WebView();
        AnchorPane.setTopAnchor(webView,0.0);
        AnchorPane.setBottomAnchor(webView,0.0);
        AnchorPane.setLeftAnchor(webView,0.0);
        AnchorPane.setRightAnchor(webView,0.0);

    }

    private void onEditButtonClicked(){
        editor.onEditButtonClicked();
        setButtonState(editor.getEditorState());
        setEditorArea(editor.getEditorState());
    }

    /**
     * decides which text the button should display
     * @param state the state of the editor (EDIT or VIEW)
     */
    private void setButtonState(EditorState state){
        if(state == EditorState.EDIT){
            editButton.setText("View");
        }else{
            editButton.setText("Edit");
        }
    }

    /**
     * decides whether to show the editor view or the rendered markdown view
     * @param state the state of the editor (EDIT or VIEW)
     */
    private void setEditorArea(EditorState state){
        if(state == EditorState.EDIT){
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(textArea);
        }else{
            webView.getEngine().loadContent("<h1 data-sourcepos=\"1:1-1:27\">this should be a headline</h1>", "text/html");
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(webView);

        }
    }


}
