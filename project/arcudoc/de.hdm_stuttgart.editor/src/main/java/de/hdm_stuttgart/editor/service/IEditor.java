package de.hdm_stuttgart.editor.service;

import de.hdm_stuttgart.editor.integration.EditorState;

public interface IEditor {

    void onSaveButtonClicked();

    void onTextFieldClicked();

    void onEditButtonClicked();

    EditorState getEditorState();
}
