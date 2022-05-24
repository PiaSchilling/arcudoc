package de.hdm_stuttgart.project;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.service.IDocu;
import de.hdm_stuttgart.editor.integration.EditorState;
import de.hdm_stuttgart.editor.service.IEditor;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class ProjectSceneController {

    private IEditor editor;
    private IDocu docu;
    private StringProperty markdownStringProperty;

    @Inject
    public ProjectSceneController(IEditor editor, IDocu docu) {
        this.editor = editor;
        this.docu = docu;
        this.markdownStringProperty = editor.getHtmlStringProperty();
    }

    // - - - - editor - - - -
    @FXML
    private Button editButton;

    @FXML
    private AnchorPane anchorPane; //todo rename

    private TextArea textArea;
    private WebView webView;

    // - - - - docu - - - - -

    @FXML
    private ImageView logo;

    @FXML
    private Label chapterAufgabenstellung;


    @FXML
    private Label chapterArchitektur;

    @FXML
    private Label chapterBausteinsichtEbeneEins;

    @FXML
    private Label chapterBausteinsichtEbeneZwei;

    @FXML
    private Label chapterEntwurfsentscheidungEins;

    @FXML
    private Label chapterEntwurfsentscheidungZwei;

    @FXML
    private Label chapterFachlStruktur;

    @FXML
    private Label chapterFachlicherKontext;

    @FXML
    private Label chapterGlossar;

    @FXML
    private Label chapterInfrastrukturEbeneEins;

    @FXML
    private Label chapterInfrastrukturEbeneZwei;

    @FXML
    private Label chapterKonventionen;

    @FXML
    private Label chapterLaufzeitszenarioEins;

    @FXML
    private Label chapterLaufzeitszenarioZwei;

    @FXML
    private Label chapterLoesungskontext;

    @FXML
    private Label chapterOrganRandbedinungen;

    @FXML
    private Label chapterQualibaum;

    @FXML
    private Label chapterQualitaetsszenarien;

    @FXML
    private Label chapterQualitaetsziele;

    @FXML
    private Label chapterRisikenUndTechnSchulden;

    @FXML
    private Label chapterStakeholder;

    @FXML
    private Label chapterTechnRandbedinungen;

    @FXML
    private Label chapterVerteilungskontext;


    public void initialize() {
        editButton.setOnAction(event -> onEditButtonClicked());
        markdownStringProperty.addListener((observable, oldValue, newValue) -> {
            setHtmlToWebView(newValue);
        });
        logo.setOnMouseClicked(event -> docu.onLogoCLicked());
        chapterAufgabenstellung.setOnMouseClicked(event -> docu.onAufgabenstellungClicked());
        chapterArchitektur.setOnMouseClicked(event -> docu.onArchitekturUndEntwurfsmusterClicked());
        chapterBausteinsichtEbeneEins.setOnMouseClicked(event -> docu.onBausteinsichtEbeneEinsClicked());
        chapterBausteinsichtEbeneZwei.setOnMouseClicked(event -> docu.onBausteinsichtEbeneZweiClicked());
        chapterEntwurfsentscheidungEins.setOnMouseClicked(event -> docu.onEntwurfsentscheidungEinsClicked());
        chapterEntwurfsentscheidungZwei.setOnMouseClicked(event -> docu.onEntwurfsentscheidnungZweiClicked());
        chapterFachlStruktur.setOnMouseClicked(event -> docu.onFachlicheStrukturClicked());
        chapterFachlicherKontext.setOnMouseClicked(event -> docu.onFachlicherKontextClicked());
        chapterGlossar.setOnMouseClicked(event -> docu.onGlossarClicked());
        chapterInfrastrukturEbeneEins.setOnMouseClicked(event -> docu.onInfrastrukturEbeneEinsClicked());
        chapterInfrastrukturEbeneZwei.setOnMouseClicked(event -> docu.onInfrastrukturEbeneZweiClicked());
        chapterKonventionen.setOnMouseClicked(event -> docu.onKonventionenClicked());
        chapterLaufzeitszenarioEins.setOnMouseClicked(event -> docu.onLaufzeitszenarioEinsCLicked());
        chapterLaufzeitszenarioZwei.setOnMouseClicked(event -> docu.onLaufzeitszenarioZweiClicked());
        chapterOrganRandbedinungen.setOnMouseClicked(event -> docu.onOrganisatorischeRandbedigungenClicked());
        chapterQualibaum.setOnMouseClicked(event -> docu.onQualitaetsbaumClicked());
        chapterQualitaetsszenarien.setOnMouseClicked(event -> docu.onQualitaetsszenarienClicked());
        chapterQualitaetsziele.setOnMouseClicked(event -> docu.onQualitaetszieleClicked());
        chapterRisikenUndTechnSchulden.setOnMouseClicked(event -> docu.onRisikenClicked());
        chapterStakeholder.setOnMouseClicked(event -> docu.onStakeholderClicked());
        chapterTechnRandbedinungen.setOnMouseClicked(event -> docu.onTechnischeRandbedigungenClicked());
        chapterVerteilungskontext.setOnMouseClicked(event -> docu.onVerteilungskontextClicked());

        anchorPane.setStyle("-fx-background-color: transparent");

        textArea = new TextArea();
        textArea.setStyle(getClass().getResource("/styles/project-screen.css").toString());
        textArea.setWrapText(true);
        textArea.setMaxWidth(anchorPane.getMaxWidth());
        AnchorPane.setTopAnchor(textArea, 0.0);
        AnchorPane.setBottomAnchor(textArea, 0.0);
        AnchorPane.setLeftAnchor(textArea, 0.0);
        AnchorPane.setRightAnchor(textArea, 0.0);

        webView = new WebView();
        AnchorPane.setTopAnchor(webView, 0.0);
        AnchorPane.setBottomAnchor(webView, 0.0);
        AnchorPane.setLeftAnchor(webView, 0.0);
        AnchorPane.setRightAnchor(webView, 0.0);

        webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/styles/webview.css").toString());

    }

    private void onEditButtonClicked() {
        editor.onEditButtonClicked(textArea.getText());
        setButtonState(editor.getEditorState());
        setEditorArea(editor.getEditorState());
    }

    /**
     * decides which text the button should display
     *
     * @param state the state of the editor (EDIT or VIEW)
     */
    private void setButtonState(EditorState state) {
        if (state == EditorState.EDIT) {
            Image img = new Image("/images/save.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(24);
            view.setPreserveRatio(true);
            editButton.setGraphic(view);

        } else {
            Image img = new Image("/images/edit.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(24);
            view.setPreserveRatio(true);
            editButton.setGraphic(view);
        }
    }

    /**
     * decides whether to show the editor view or the rendered markdown view
     *
     * @param state the state of the editor (EDIT or VIEW)
     */
    private void setEditorArea(EditorState state) {
        if (state == EditorState.EDIT) {
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(textArea);
        } else {
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(webView);

        }
    }

    private void setHtmlToWebView(String html) {
        Platform.runLater(() -> webView.getEngine().loadContent(html, "text/html"));
    }


}
