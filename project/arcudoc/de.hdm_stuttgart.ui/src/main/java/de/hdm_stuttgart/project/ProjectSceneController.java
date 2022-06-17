package de.hdm_stuttgart.project;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import de.hdm_stuttgart.docu.service.IDocu;
import de.hdm_stuttgart.docu.service.ITemplateResponse;
import de.hdm_stuttgart.editor.service.EditorState;
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
    private ITemplateResponse template;
    private StringProperty markdownStringProperty;
    private ITemplateResponse templateResponse;



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
    private Label chapterNumber;

    @FXML
    private Label chapterTitle;

    @FXML
    private Label subchapterNumber;

    @FXML
    private Label subchapterTitle;

    @FXML
    private Label titleRandbedingungen;

    @FXML
    private Label titleKontextabgrenzung;

    @FXML
    private Label titleBausteineinsicht;

    @FXML
    private Label titleVerteilungssicht;

    @FXML
    private Label titleQuerschnitt;

    @FXML
    private Label titleEntwurfsentscheidung;

    @FXML
    private Label titleQualität;

    @FXML
    private Label titleEinfuehrungUndZiele;

    @FXML
    private Label titleLaufzeisicht;

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

        logo.setOnMouseClicked(event -> onLogoCLicked());
        titleEinfuehrungUndZiele.setOnMouseClicked(event -> onEinfuehrungUndZieleClicked());
        titleRandbedingungen.setOnMouseClicked(event -> onRandbedingungenClicked());
        titleKontextabgrenzung.setOnMouseClicked(event -> onKontextabgrenzungClicked());
        titleBausteineinsicht.setOnMouseClicked(event -> onBausteinsichtClicked());
        titleLaufzeisicht.setOnMouseClicked(event -> onLaufzeitsichtClicked());
        titleVerteilungssicht.setOnMouseClicked(event -> onVerteilungssichtClicked());
        titleQuerschnitt.setOnMouseClicked(event -> onQuerschnittClicked());
        titleEntwurfsentscheidung.setOnMouseClicked(event -> onEntwurfentscheidungenClicked());
        titleQualität.setOnMouseClicked(event -> onQualitaetsanforderungenClicked());
        chapterAufgabenstellung.setOnMouseClicked(event -> onAufgabenstellungClicked());
        chapterArchitektur.setOnMouseClicked(event -> onArchitekturUndEntwurfsmusterClicked());
        chapterBausteinsichtEbeneEins.setOnMouseClicked(event -> onBausteinsichtEbeneEinsClicked());
        chapterBausteinsichtEbeneZwei.setOnMouseClicked(event -> onBausteinsichtEbeneZweiClicked());
        chapterEntwurfsentscheidungEins.setOnMouseClicked(event -> onEntwurfsentscheidungEinsClicked());
        chapterEntwurfsentscheidungZwei.setOnMouseClicked(event -> onEntwurfsentscheidnungZweiClicked());
        chapterFachlStruktur.setOnMouseClicked(event -> onFachlicheStrukturClicked());
        chapterFachlicherKontext.setOnMouseClicked(event -> onFachlicherKontextClicked());
        chapterGlossar.setOnMouseClicked(event -> onGlossarClicked());
        chapterInfrastrukturEbeneEins.setOnMouseClicked(event -> onInfrastrukturEbeneEinsClicked());
        chapterInfrastrukturEbeneZwei.setOnMouseClicked(event -> onInfrastrukturEbeneZweiClicked());
        chapterKonventionen.setOnMouseClicked(event -> onKonventionenClicked());
        chapterLaufzeitszenarioEins.setOnMouseClicked(event -> onLaufzeitszenarioEinsCLicked());
        chapterLaufzeitszenarioZwei.setOnMouseClicked(event -> onLaufzeitszenarioZweiClicked());
        chapterLoesungskontext.setOnMouseClicked(event -> onLoesungsstrategieClicked());
        chapterOrganRandbedinungen.setOnMouseClicked(event -> onOrganisatorischeRandbedigungenClicked());
        chapterQualibaum.setOnMouseClicked(event -> onQualitaetsbaumClicked());
        chapterQualitaetsszenarien.setOnMouseClicked(event -> onQualitaetsszenarienClicked());
        chapterQualitaetsziele.setOnMouseClicked(event -> onQualitaetszieleClicked());
        chapterRisikenUndTechnSchulden.setOnMouseClicked(event -> onRisikenClicked());
        chapterStakeholder.setOnMouseClicked(event -> onStakeholderClicked());
        chapterTechnRandbedinungen.setOnMouseClicked(event -> onTechnischeRandbedigungenClicked());
        chapterVerteilungskontext.setOnMouseClicked(event -> onVerteilungskontextClicked());

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

        editor.getEditorState();

         templateResponse = docu.fetchTemplate();
        System.out.println(templateResponse.getTemplate().get(0).getAsJsonObject().get("1.0_Einführung-und-Ziele").getAsString());

        fillTemplate("1.0_Einführung-und-Ziele");





    }

    private void onEditButtonClicked() {
        editor.onEditButtonClicked(textArea.getText());
        setButtonState(editor.getEditorState());
        setEditorArea(editor.getEditorState());

        //TODO Pass content to repo and send it to database
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

    // - - - - docu logic - - - - -


    public void onLogoCLicked() {

        //TODO open project Overview




    }

    public void onEinfuehrungUndZieleClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("1.0_Einführung-und-Ziele");

    }


    public void onAufgabenstellungClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText("Aufgabenstellung");
        subchapterNumber.setText("01.1");

        fillTemplate("1.1_Aufgabenstellung");
    }


    public void onQualitaetszieleClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText("Qualitätsziele");
        subchapterNumber.setText("01.2");
        fillTemplate("1.2_Qualiteatsziele");
    }


    public void onStakeholderClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText("Stakeholder");
        subchapterNumber.setText("01.3");
        fillTemplate("1.3_Stakeholder");
    }

    public void onRandbedingungenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("2.0_Randbedingungen");
    }


    public void onTechnischeRandbedigungenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText("Technische Randbedingungen");
        subchapterNumber.setText("02.1");
        fillTemplate("2.1_Technische-Randbedingungen");
    }


    public void onOrganisatorischeRandbedigungenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText("Organisatorische Randbedingungen");
        subchapterNumber.setText("02.2");
        fillTemplate("2.2_Organisatorische-Randbedingungen");
    }


    public void onKonventionenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText("Konventionen");
        subchapterNumber.setText("02.3");
        fillTemplate("2.3_Konventionen");
    }


    public void onKontextabgrenzungClicked() {

        chapterTitle.setText("Kontextabgrenzung");
        chapterNumber.setText("03");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("3.0_Kontextabgrenzug");
    }

    public void onFachlicherKontextClicked() {

        chapterTitle.setText("Kontextabgrenzung");
        chapterNumber.setText("03");
        subchapterTitle.setText("Fachlicher Kontext");
        subchapterNumber.setText("03.1");
        fillTemplate("3.1_Fachlicher-Kontext");
    }


    public void onVerteilungskontextClicked() {

        chapterTitle.setText("Kontextabgrenzung");
        chapterNumber.setText("03");
        subchapterTitle.setText("Verteilungskontext");
        subchapterNumber.setText("03.2");
        fillTemplate("3.2_Technischer-Verteilungskontexz");
    }


    public void onLoesungsstrategieClicked() {

        chapterTitle.setText("Lösungsstrategie");
        chapterNumber.setText("04");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("4.0_Lösungsstrategien");
    }


    public void onBausteinsichtClicked() {

        chapterTitle.setText("Bausteinsicht");
        chapterNumber.setText("05");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("5.0_Bausteinsicht");
    }


    public void onBausteinsichtEbeneEinsClicked() {

        chapterTitle.setText("Bausteinsicht");
        chapterNumber.setText("05");
        subchapterTitle.setText("Ebene 1");
        subchapterNumber.setText("05.1");
        fillTemplate("5.1_Ebene-1");
    }


    public void onBausteinsichtEbeneZweiClicked() {

        chapterTitle.setText("Bausteinsicht");
        chapterNumber.setText("05");
        subchapterTitle.setText("Ebene 2");
        subchapterNumber.setText("05.2");
        fillTemplate("5.1_Ebene-1");
    }


    public void onLaufzeitsichtClicked() {

        chapterTitle.setText("Laufzeitsicht");
        chapterNumber.setText("06");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("6.0_Laufzeitsicht");
    }


    public void onLaufzeitszenarioEinsCLicked() {

        chapterTitle.setText("Laufzeitsicht");
        chapterNumber.setText("06");
        subchapterTitle.setText("Laufzeitszenario 1");
        subchapterNumber.setText("06.1");
        fillTemplate("6.1_Laufzeitszenario-1");
    }


    public void onLaufzeitszenarioZweiClicked() {

        chapterTitle.setText("Laufzeitsicht");
        chapterNumber.setText("06");
        subchapterTitle.setText("Laufzeitszenario 2");
        subchapterNumber.setText("06.2");
        fillTemplate("6.1_Laufzeitszenario-1");
    }


    public void onVerteilungssichtClicked() {

        chapterTitle.setText("Verteilungssicht");
        chapterNumber.setText("07");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("7.0_Verteilungssicht");
    }


    public void onInfrastrukturEbeneEinsClicked() {

        chapterTitle.setText("Verteilungssicht");
        chapterNumber.setText("07");
        subchapterTitle.setText("Infrastruktur Ebene 1");
        subchapterNumber.setText("07.1");
        fillTemplate("7.1_Infrastrukur-Ebene-1");
    }


    public void onInfrastrukturEbeneZweiClicked() {

        chapterTitle.setText("Verteilungssicht");
        chapterNumber.setText("07");
        subchapterTitle.setText("Infrastruktur Ebene 2");
        subchapterNumber.setText("07.2");
        fillTemplate("7.2_Infrastrukur-Ebene-2");
    }


    public void onQuerschnittClicked() {

        chapterTitle.setText("Querschnittliche Konzepte");
        chapterNumber.setText("08");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("8.0_Querschnittliche-Konzepte");
    }


    public void onFachlicheStrukturClicked() {

        chapterTitle.setText("Querschnittliche Konzepte");
        chapterNumber.setText("08");
        subchapterTitle.setText("Fachliche Struktur und Modelle");
        subchapterNumber.setText("08.1");
        fillTemplate("8.0_Querschnittliche-Konzepte");
    }


    public void onArchitekturUndEntwurfsmusterClicked() {

        chapterTitle.setText("Querschnittliche Konzepte");
        chapterNumber.setText("08");
        subchapterTitle.setText("Architektur- und Entwurfsmuster");
        subchapterNumber.setText("08.2");
        fillTemplate("8.0_Querschnittliche-Konzepte");
    }


    public void onEntwurfentscheidungenClicked() {

        chapterTitle.setText("Entwurfsentscheidungen");
        chapterNumber.setText("09");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("9.0_Entwurfsentscheidung");
    }


    public void onEntwurfsentscheidungEinsClicked() {

        chapterTitle.setText("Entwurfsentscheidungen");
        chapterNumber.setText("09");
        subchapterTitle.setText("Entwurfsentscheidung 1");
        subchapterNumber.setText("09.1");
        fillTemplate("9.1_Entwurfsentscheidung-1");
    }


    public void onEntwurfsentscheidnungZweiClicked() {

        chapterTitle.setText("Entwurfsentscheidungen");
        chapterNumber.setText("09");
        subchapterTitle.setText("Entwurfsentscheidung 2");
        subchapterNumber.setText("09.2");
        fillTemplate("9.1_Entwurfsentscheidung-1");
    }


    public void onQualitaetsanforderungenClicked() {

        chapterTitle.setText("Qualitätsanforderungen");
        chapterNumber.setText("10");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("10.1_Qualitätsanforderungen");
    }


    public void onQualitaetsbaumClicked() {

        chapterTitle.setText("Qualitätsanforderungen");
        chapterNumber.setText("10");
        subchapterTitle.setText("Qualitätsbaum");
        subchapterNumber.setText("10.1");
        fillTemplate("10.1_Qualitätsbaum");
    }


    public void onQualitaetsszenarienClicked() {

        chapterTitle.setText("Qualitätsanforderungen");
        chapterNumber.setText("10");
        subchapterTitle.setText("Qualitätsszenarien");
        subchapterNumber.setText("10.2");
        fillTemplate("10.2_Qualitätsszenarien");
    }


    public void onRisikenClicked() {

        chapterTitle.setText("Risiken und technische Strukturen");
        chapterNumber.setText("11");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("11.0_Risiken-und-technische-Schulden");

    }


    public void onGlossarClicked() {

        chapterTitle.setText("Glossar");
        chapterNumber.setText("12");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        fillTemplate("12.0_Glossar");
    }

    public void fillTemplate(String temp){

        setHtmlToWebView(templateResponse.getTemplate().get(0).getAsJsonObject().get(temp).getAsString());
        setEditorArea(EditorState.VIEW);

    }

}
