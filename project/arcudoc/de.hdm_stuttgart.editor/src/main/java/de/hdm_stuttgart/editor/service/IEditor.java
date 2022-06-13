package de.hdm_stuttgart.editor.service;

import de.hdm_stuttgart.editor.integration.EditorState;
import javafx.beans.property.StringProperty;

public interface IEditor {

    void onSaveButtonClicked();

    void onTextFieldClicked();

    void onEditButtonClicked(String markdownInput);

    EditorState getEditorState();

    StringProperty getHtmlStringProperty();

}
