package de.hdm_stuttgart.editor.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.service.IEditor;
import javafx.beans.property.StringProperty;

public class Editor implements IEditor {

    private EditorState editorState = EditorState.VIEW;
    private EditorController editorController;

    private final StringProperty htmlStringProperty;
    private String markdownInput = "";

    @Inject
    public Editor(EditorController editorController) {
        this.editorController = editorController;
        htmlStringProperty = editorController.getHtmlStringProperty();

    }

    @Override
    public void onSaveButtonClicked() {
        editorState = EditorState.VIEW;

    }

    public void setMarkdownInput(String markdownInput){
        this.markdownInput = markdownInput;
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
            editorController.renderMarkdownToHtml("# heading1");
        }
    }

    public EditorState getEditorState() {
        return editorState;
    }

    public StringProperty getHtmlStringProperty() {
        return htmlStringProperty;
    }


}
