package de.hdm_stuttgart.project;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.integration.EditorState;
import de.hdm_stuttgart.editor.service.IEditor;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class ProjectSceneController {

    private IEditor editor;
    private StringProperty markdownStringProperty;

    @Inject
    public ProjectSceneController(IEditor editor){
        this.editor = editor;
        this.markdownStringProperty = editor.getHtmlStringProperty();
    }

    @FXML
    private Button editButton;

    @FXML
    private AnchorPane anchorPane; //todo rename

    private TextArea textArea;
    private WebView webView;

    public void initialize() {
        editButton.setOnAction(event -> onEditButtonClicked());
        markdownStringProperty.addListener((observable, oldValue, newValue) -> {
            setHtmlToWebView(newValue);
        });

        anchorPane.setStyle("-fx-background-color: transparent");

        textArea = new TextArea();
        textArea.setStyle(getClass().getResource("/styles/project-screen.css").toString());
        textArea.setWrapText(true);
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

        webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/styles/webview.css").toString());

    }

    private void onEditButtonClicked(){
        editor.onEditButtonClicked(textArea.getText());
        setButtonState(editor.getEditorState());
        setEditorArea(editor.getEditorState());
    }

    /**
     * decides which text the button should display
     * @param state the state of the editor (EDIT or VIEW)
     */
    private void setButtonState(EditorState state){
        if(state == EditorState.EDIT){
            Image img = new Image("/images/save.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(24);
            view.setPreserveRatio(true);
            editButton.setGraphic(view);

        }else{
            Image img = new Image("/images/edit.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(24);
            view.setPreserveRatio(true);
            editButton.setGraphic(view);
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
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(webView);

        }
    }

    private void setHtmlToWebView(String html){
        Platform.runLater(() -> webView.getEngine().loadContent(html, "text/html"));
    }


}
