package de.hdm_stuttgart.editor.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EditorTest {

    private Editor editor;

    @BeforeEach
    void setUp() {
        EditorController mockController = Mockito.mock(EditorController.class);
        editor = new Editor(mockController);
    }

    @Test
    public void should_returnViewState_When_ClassStateInitial(){
        assertEquals(EditorState.VIEW,editor.getEditorState());
        assertNotEquals(EditorState.EDIT,editor.getEditorState());
    }


    @Test
    public void should_returnEditState_When_EditButtonClicked(){
        editor.onEditButtonClicked("test");
        assertEquals(EditorState.EDIT,editor.getEditorState());
        assertNotEquals(EditorState.VIEW,editor.getEditorState());
    }

    @Test
    public void should_returnViewState_When_EditButtonClickedTwice(){
        editor.onEditButtonClicked("test");
        editor.onEditButtonClicked("test");
        assertEquals(EditorState.VIEW,editor.getEditorState());
        assertNotEquals(EditorState.EDIT,editor.getEditorState());
    }
}