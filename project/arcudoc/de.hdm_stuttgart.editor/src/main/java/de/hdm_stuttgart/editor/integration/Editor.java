package de.hdm_stuttgart.editor.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.service.EditorState;
import de.hdm_stuttgart.editor.service.IEditor;
import javafx.beans.property.StringProperty;

public class Editor implements IEditor {

    private EditorState editorState = EditorState.VIEW;
    private final EditorController editorController;

    private final StringProperty htmlStringProperty;

    @Inject
    public Editor(EditorController editorController) {
        this.editorController = editorController;
        htmlStringProperty = editorController.getHtmlStringProperty();

    }

    @Override
    public void onSaveButtonClicked() {
        editorState = EditorState.VIEW;

    }

    @Override
    public void onTextFieldClicked() {

    }

    @Override
    public void onEditButtonClicked(String markdownInput) {
        if(editorState == EditorState.EDIT){
            editorState = EditorState.VIEW;
            editorController.renderMarkdownToHtml(markdownInput);
        }else{
            editorState = EditorState.EDIT;
            editorController.renderMarkdownToHtml("*loading...*");
        }
    }

    @Override
    public void renderMarkdownClicked(String markdownInput) {

        editorController.renderMarkdownToHtml(markdownInput);

    }

    public EditorState getEditorState() {
        return editorState;
    }

    public StringProperty getHtmlStringProperty() {
        return htmlStringProperty;
    }


}
