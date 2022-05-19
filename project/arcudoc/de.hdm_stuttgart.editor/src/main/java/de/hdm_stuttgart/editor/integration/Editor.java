package de.hdm_stuttgart.editor.integration;

import de.hdm_stuttgart.editor.service.IEditor;

public class Editor implements IEditor {

    private EditorState editorState = EditorState.VIEW;


    @Override
    public void onSaveButtonClicked() {
        editorState = EditorState.VIEW;

    }

    @Override
    public void onTextFieldClicked() {

    }

    @Override
    public void onEditButtonClicked() {

        if(editorState == EditorState.EDIT){
            editorState = EditorState.VIEW;
        }else{
            editorState = EditorState.EDIT;
        }
    }

    public EditorState getEditorState() {
        return editorState;
    }
}
