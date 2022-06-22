package de.hdm_stuttgart.project;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.service.IDocu;
import de.hdm_stuttgart.docu.service.ITemplateResponse;
import de.hdm_stuttgart.editor.service.EditorState;
import de.hdm_stuttgart.editor.service.IEditor;
import de.hdm_stuttgart.navigation.NavigationController;
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
    private ITemplateResponse templateResponse;
    private int projectId;


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
    private Label nameOfProject;

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

    private String property = "1.1_Aufgabenstellung";


    public void initialize() {

        editButton.setOnAction(event -> onEditButtonClicked());

        markdownStringProperty.addListener((observable, oldValue, newValue) -> {
            setHtmlToWebView(newValue);
        });

        logo.setOnMouseClicked(event -> NavigationController.getINSTANCE().showWorkspaceScene());

        setChapterOnClicks();

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
    }

    /**
     * sets the project id which indicates which project content is displayed in the view
     * @param projectId the id of the project of which the content should be displayed
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
        initEditorContent(projectId);
    }

    /**
     * fetch project content and set first chapter to the view
     * @param projectId the id of the project for which the content should be fetched
     */
    private void initEditorContent(int projectId){
        templateResponse = docu.fetchTemplate(projectId);
       // nameOfProject.setText(templateResponse.getTitle());
        fillWithContent("1.1_Aufgabenstellung");
        editor.renderMarkdownClicked(textArea.getText());
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

    /**
     * sets html content to the webview that it can be rendered
     * @param html
     */
    private void setHtmlToWebView(String html) {
        //todo escape javascript
        Platform.runLater(() -> webView.getEngine().loadContent(html, "text/html"));
    }

    private void setChapterOnClicks(){

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
        saveContent(property, textArea.getText());
        property = "1.0_Einführung-und-Ziele";
        fillWithContent(property);


    }


    public void onAufgabenstellungClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText("Aufgabenstellung");
        subchapterNumber.setText("01.1");
        saveContent(property, textArea.getText());
        property = "1.1_Aufgabenstellung";
        fillWithContent(property);
    }


    public void onQualitaetszieleClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText("Qualitätsziele");
        subchapterNumber.setText("01.2");
        saveContent(property, textArea.getText());
        property = "1.2_Qualitaetsziele";
        fillWithContent(property);

    }


    public void onStakeholderClicked() {

        chapterTitle.setText("Einführung und Ziele");
        chapterNumber.setText("01");
        subchapterTitle.setText("Stakeholder");
        subchapterNumber.setText("01.3");
        saveContent(property, textArea.getText());
        property = "1.3_Stakeholder";
        fillWithContent(property);
    }

    public void onRandbedingungenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "2.0_Randbedingungen";
        fillWithContent(property);
    }


    public void onTechnischeRandbedigungenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText("Technische Randbedingungen");
        subchapterNumber.setText("02.1");
        saveContent(property, textArea.getText());
        property = "2.1_Technische-Randbedingungen";
        fillWithContent(property);
    }


    public void onOrganisatorischeRandbedigungenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText("Organisatorische Randbedingungen");
        subchapterNumber.setText("02.2");
        saveContent(property, textArea.getText());
        property = "2.2_Organisatorische-Randbedingungen";
        fillWithContent(property);
    }


    public void onKonventionenClicked() {

        chapterTitle.setText("Randbedingungen");
        chapterNumber.setText("02");
        subchapterTitle.setText("Konventionen");
        subchapterNumber.setText("02.3");
        saveContent(property, textArea.getText());
        property = "2.3_Konventionen";
        fillWithContent(property);
    }


    public void onKontextabgrenzungClicked() {

        chapterTitle.setText("Kontextabgrenzung");
        chapterNumber.setText("03");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "3.0_Kontextabgrenzug";
        fillWithContent(property);
    }

    public void onFachlicherKontextClicked() {

        chapterTitle.setText("Kontextabgrenzung");
        chapterNumber.setText("03");
        subchapterTitle.setText("Fachlicher Kontext");
        subchapterNumber.setText("03.1");
        saveContent(property, textArea.getText());
        property =  "3.1_Fachlicher-Kontext" ;
        fillWithContent(property);
    }


    public void onVerteilungskontextClicked() {

        chapterTitle.setText("Kontextabgrenzung");
        chapterNumber.setText("03");
        subchapterTitle.setText("Verteilungskontext");
        subchapterNumber.setText("03.2");
        saveContent(property, textArea.getText());
        property = "3.2_Technischer-Verteilungskontext";
        fillWithContent(property);
    }


    public void onLoesungsstrategieClicked() {

        chapterTitle.setText("Lösungsstrategie");
        chapterNumber.setText("04");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "4.0_Loesungsstrategien";
        fillWithContent(property);
    }


    public void onBausteinsichtClicked() {

        chapterTitle.setText("Bausteinsicht");
        chapterNumber.setText("05");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "5.0_Bausteinsicht";
        fillWithContent(property);
    }


    public void onBausteinsichtEbeneEinsClicked() {

        chapterTitle.setText("Bausteinsicht");
        chapterNumber.setText("05");
        subchapterTitle.setText("Ebene 1");
        subchapterNumber.setText("05.1");
        saveContent(property, textArea.getText());
        property = "5.1_Ebene-1";
        fillWithContent(property);
    }


    public void onBausteinsichtEbeneZweiClicked() {

        chapterTitle.setText("Bausteinsicht");
        chapterNumber.setText("05");
        subchapterTitle.setText("Ebene 2");
        subchapterNumber.setText("05.2");
        saveContent(property, textArea.getText());
        property = "5.2_Ebene-2";
        fillWithContent(property);
    }


    public void onLaufzeitsichtClicked() {

        chapterTitle.setText("Laufzeitsicht");
        chapterNumber.setText("06");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "6.0_Laufzeitsicht";
        fillWithContent(property);
    }


    public void onLaufzeitszenarioEinsCLicked() {

        chapterTitle.setText("Laufzeitsicht");
        chapterNumber.setText("06");
        subchapterTitle.setText("Laufzeitszenario 1");
        subchapterNumber.setText("06.1");
        saveContent(property, textArea.getText());
        property = "6.1_Laufzeitszenario-1";
        fillWithContent(property);
    }


    public void onLaufzeitszenarioZweiClicked() {

        chapterTitle.setText("Laufzeitsicht");
        chapterNumber.setText("06");
        subchapterTitle.setText("Laufzeitszenario 2");
        subchapterNumber.setText("06.2");
        saveContent(property, textArea.getText());
        property = "6.2_Laufzeitszenario-2";
        fillWithContent(property);
    }


    public void onVerteilungssichtClicked() {

        chapterTitle.setText("Verteilungssicht");
        chapterNumber.setText("07");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "7.0_Verteilungssicht";
        fillWithContent(property);
    }


    public void onInfrastrukturEbeneEinsClicked() {

        chapterTitle.setText("Verteilungssicht");
        chapterNumber.setText("07");
        subchapterTitle.setText("Infrastruktur Ebene 1");
        subchapterNumber.setText("07.1");
        saveContent(property, textArea.getText());
        property = "7.1_Infrastrukur-Ebene-1";
        fillWithContent(property);
    }


    public void onInfrastrukturEbeneZweiClicked() {

        chapterTitle.setText("Verteilungssicht");
        chapterNumber.setText("07");
        subchapterTitle.setText("Infrastruktur Ebene 2");
        subchapterNumber.setText("07.2");
        saveContent(property, textArea.getText());
        property = "7.2_Infrastrukur-Ebene-2";
        fillWithContent(property);
    }


    public void onQuerschnittClicked() {

        chapterTitle.setText("Querschnittliche Konzepte");
        chapterNumber.setText("08");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "8.0_Querschnittliche-Konzepte";
        fillWithContent(property);
    }


    public void onFachlicheStrukturClicked() {

        chapterTitle.setText("Querschnittliche Konzepte");
        chapterNumber.setText("08");
        subchapterTitle.setText("Fachliche Struktur und Modelle");
        subchapterNumber.setText("08.1");
        saveContent(property, textArea.getText());
        property = "8.1_Struktur-Modelle";
        fillWithContent(property);
    }


    public void onArchitekturUndEntwurfsmusterClicked() {

        chapterTitle.setText("Querschnittliche Konzepte");
        chapterNumber.setText("08");
        subchapterTitle.setText("Architektur- und Entwurfsmuster");
        subchapterNumber.setText("08.2");
        saveContent(property, textArea.getText());
        property = "8.2_Architektur";
        fillWithContent(property);
    }


    public void onEntwurfentscheidungenClicked() {

        chapterTitle.setText("Entwurfsentscheidungen");
        chapterNumber.setText("09");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "9.0_Entwurfsentscheidung";
        fillWithContent(property);
    }


    public void onEntwurfsentscheidungEinsClicked() {

        chapterTitle.setText("Entwurfsentscheidungen");
        chapterNumber.setText("09");
        subchapterTitle.setText("Entwurfsentscheidung 1");
        subchapterNumber.setText("09.1");
        saveContent(property, textArea.getText());
        property = "9.1_Entwurfsentscheidung-1";
        fillWithContent(property);
    }


    public void onEntwurfsentscheidnungZweiClicked() {

        chapterTitle.setText("Entwurfsentscheidungen");
        chapterNumber.setText("09");
        subchapterTitle.setText("Entwurfsentscheidung 2");
        subchapterNumber.setText("09.2");
        saveContent(property, textArea.getText());
        property = "9.2_Entwurfsentscheidung-2";
        fillWithContent(property);
    }


    public void onQualitaetsanforderungenClicked() {

        chapterTitle.setText("Qualitätsanforderungen");
        chapterNumber.setText("10");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property =  "10.0_Qualitätsanforderungen";
        fillWithContent(property);
    }


    public void onQualitaetsbaumClicked() {

        chapterTitle.setText("Qualitätsanforderungen");
        chapterNumber.setText("10");
        subchapterTitle.setText("Qualitätsbaum");
        subchapterNumber.setText("10.1");
        saveContent(property, textArea.getText());
        property =  "10.1_Qualitätsbaum" ;
        fillWithContent(property);
    }


    public void onQualitaetsszenarienClicked() {

        chapterTitle.setText("Qualitätsanforderungen");
        chapterNumber.setText("10");
        subchapterTitle.setText("Qualitätsszenarien");
        subchapterNumber.setText("10.2");
        saveContent(property, textArea.getText());
        property = "10.2_Qualitätsszenarien";
        fillWithContent(property);
    }


    public void onRisikenClicked() {

        chapterTitle.setText("Risiken und technische Strukturen");
        chapterNumber.setText("11");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "11.0_Risiken-und-technische-Schulden";
        fillWithContent(property);

    }


    public void onGlossarClicked() {

        chapterTitle.setText("Glossar");
        chapterNumber.setText("12");
        subchapterTitle.setText(null);
        subchapterNumber.setText(null);
        saveContent(property, textArea.getText());
        property = "12.0_Glossar";
        fillWithContent(property);
    }

    public void fillWithContent(String property){


        switch (property){

            case "1.0_Einführung-und-Ziele":
                textArea.setText(templateResponse.getEinfuehrungUndZiele());
                break;
            case "1.1_Aufgabenstellung":
                textArea.setText(templateResponse.getAufgabenstellung());
                break;
            case "1.2_Qualitaetsziele":
                textArea.setText(templateResponse.getQualiteatsziele());
                break;
            case "1.3_Stakeholder":
                textArea.setText(templateResponse.getStakeholder());
                break;
            case "2.0_Randbedingungen":
                textArea.setText(templateResponse.getRandbedingungen());
                break;
            case "2.1_Technische-Randbedingungen":
                textArea.setText(templateResponse.getTechnischeRandbedingungen());
                break;
            case "2.2_Organisatorische-Randbedingungen":
                textArea.setText(templateResponse.getOrganisatorischeRandbedingungen());
                break;
            case "2.3_Konventionen":
                textArea.setText(templateResponse.getKonventionen());
                break;
            case "3.0_Kontextabgrenzug":
                textArea.setText(templateResponse.getKontextabgrenzug());
                break;
            case "3.1_Fachlicher-Kontext":
                textArea.setText(templateResponse.getFachlicherKontext());
                break;
            case "3.2_Technischer-Verteilungskontext":
                textArea.setText(templateResponse.getTechnischerVerteilungskontext());
                break;
            case "4.0_Loesungsstrategien":
                textArea.setText(templateResponse.getLoesungsstrategien());
                break;
            case "5.0_Bausteinsicht":
                textArea.setText(templateResponse.getBausteinsicht());
                break;
            case "5.1_Ebene-1":
                textArea.setText(templateResponse.getBausteinsichtEbeneEins());
                break;
            case "5.2_Ebene-2":
                textArea.setText(templateResponse.getBausteinsichtEbendeZwei());
                break;
            case "6.0_Laufzeitsicht":
                textArea.setText(templateResponse.getLaufzeitsicht());
                break;
            case "6.1_Laufzeitszenario-1":
                textArea.setText(templateResponse.getLaufzeitsichtSzenarioEins());
                break;
            case "6.2_Laufzeitszenario-2":
                textArea.setText(templateResponse.getLaufzeitsichtSzenarioZwei());
                break;
            case "7.0_Verteilungssicht":
                textArea.setText(templateResponse.getVertreilungssicht());
                break;
            case "7.1_Infrastrukur-Ebene-1":
                textArea.setText(templateResponse.getInfrastrukurEbeneEins());
                break;
            case "7.2_Infrastrukur-Ebene-2":
                textArea.setText(templateResponse.getInfrastrukurEbeneZwei());
                break;
            case "8.0_Querschnittliche-Konzepte":
                textArea.setText(templateResponse.getQuerschnittlicheKonzepte());
                break;
            case "8.1_Struktur-Modelle":
                textArea.setText(templateResponse.getFachStrukturUndModelle());
                break;
            case "8.2_Architektur":
                textArea.setText(templateResponse.getArchitekturUndEntwurfsmuster());
                break;
            case "9.0_Entwurfsentscheidung":
                textArea.setText(templateResponse.getEntwurfsentscheidung());
                break;
            case "9.1_Entwurfsentscheidung-1":
                textArea.setText(templateResponse.getEntwurfsentscheidungEins());
                break;
            case "9.2_Entwurfsentscheidung-2":
                textArea.setText(templateResponse.getEntwurfsentscheidungZwei());
                break;
            case "10.0_Qualitätsanforderungen":
                textArea.setText(templateResponse.getQualitätsanforderungen());
                break;
            case "10.1_Qualitätsbaum":
                textArea.setText(templateResponse.getQualitätsbaum());
                break;
            case "10.2_Qualitätsszenarien":
                textArea.setText(templateResponse.getQualitätszenarien());
                break;
            case "11.0_Risiken-und-technische-Schulden":
                textArea.setText(templateResponse.getRisikenUndTechnischeSchulden());
                break;
            case "12.0_Glossar":
                textArea.setText(templateResponse.getGlosar());
                break;

        }


        editor.renderMarkdownClicked(textArea.getText());
        setEditorArea(EditorState.VIEW);

    }

    public void saveContent(String property, String value){


        switch (property){

            case "1.0_Einführung-und-Ziele":
                templateResponse.setEinfuehrungUndZiele(value);
                break;
            case "1.1_Aufgabenstellung":
                templateResponse.setAufgabenstellung(value);
                break;
            case "1.2_Qualitaetsziele":
                templateResponse.setQualiteatsziele(value);
                break;
            case "1.3_Stakeholder":
                templateResponse.setStakeholder(value);
                break;
            case "2.0_Randbedingungen":
                templateResponse.setRandbedingungen(value);
                break;
            case "2.1_Technische-Randbedingungen":
                templateResponse.setTechnischeRandbedingungen(value);
                break;
            case "2.2_Organisatorische-Randbedingungen":
                templateResponse.setOrganisatorischeRandbedingungen(value);
                break;
            case "2.3_Konventionen":
                templateResponse.setKonventionen(value);
                break;
            case "3.0_Kontextabgrenzug":
                templateResponse.setKontextabgrenzug(value);
                break;
            case "3.1_Fachlicher-Kontext":
                templateResponse.setFachlicherKontext(value);
                break;
            case "3.2_Technischer-Verteilungskontext":
                templateResponse.setTechnischerVerteilungskontext(value);
                break;
            case "4.0_Loesungsstrategien":
                templateResponse.setLoesungsstrategien(value);
                break;
            case "5.0_Bausteinsicht":
                templateResponse.setBausteinsicht(value);
                break;
            case "5.1_Ebene-1":
                templateResponse.setBausteinsichtEbeneEins(value);
                break;
            case "5.2_Ebene-2":
                templateResponse.setBausteinsichtEbendeZwei(value);
                break;
            case "6.0_Laufzeitsicht":
                templateResponse.setLaufzeitsicht(value);
                break;
            case "6.1_Laufzeitszenario-1":
                templateResponse.setLaufzeitsichtSzenarioEins(value);
                break;
            case "6.2_Laufzeitszenario-2":
                templateResponse.setLaufzeitsichtSzenarioZwei(value);
                break;
            case "7.0_Verteilungssicht":
                templateResponse.setVertreilungssicht(value);
                break;
            case "7.1_Infrastrukur-Ebene-1":
                templateResponse.setInfrastrukurEbeneEins(value);
                break;
            case "7.2_Infrastrukur-Ebene-2":
                templateResponse.setInfrastrukurEbeneZwei(value);
                break;
            case "8.0_Querschnittliche-Konzepte":
                templateResponse.setQuerschnittlicheKonzepte(value);
                break;
            case "8.1_Struktur-Modelle":
                templateResponse.setFachStrukturUndModelle(value);
                break;
            case "8.2_Architektur":
                templateResponse.setArchitekturUndEntwurfsmuster(value);
                break;
            case "9.0_Entwurfsentscheidung":
                templateResponse.setEntwurfsentscheidung(value);
                break;
            case "9.1_Entwurfsentscheidung-1":
                templateResponse.setEntwurfsentscheidungEins(value);
                break;
            case "9.2_Entwurfsentscheidung-2":
                templateResponse.setEntwurfsentscheidungZwei(value);
                break;
            case "10.0_Qualitätsanforderungen":
                templateResponse.setQualitätsanforderungen(value);
                break;
            case "10.1_Qualitätsbaum":
                templateResponse.setQualitätsbaum(value);
                break;
            case "10.2_Qualitätsszenarien":
                templateResponse.setQualitätszenarien(value);
                break;
            case "11.0_Risiken-und-technische-Schulden":
                templateResponse.setRisikenUndTechnischeSchulden(value);
                break;
            case "12.0_Glossar":
                templateResponse.setGlosar(value);
                break;


        }

        docu.setContent(templateResponse);
    }

}
