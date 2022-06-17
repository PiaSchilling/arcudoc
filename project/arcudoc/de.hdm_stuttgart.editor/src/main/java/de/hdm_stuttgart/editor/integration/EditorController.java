package de.hdm_stuttgart.editor.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.data.IEditorRepo;
import javafx.beans.property.StringProperty;

public class EditorController {

    private final IEditorRepo editorRepo;

    @Inject
    public EditorController(IEditorRepo editorRepo) {
        this.editorRepo = editorRepo;
    }

    /**
     * calls repo which converts markdown to html
     * @param textToRender the markdown string which should be converted
     */
    public void renderMarkdownToHtml(String textToRender){
        editorRepo.fetchMarkDown(textToRender);
    }

    public StringProperty getHtmlStringProperty() {
        return editorRepo.getHtmlStringProperty();
    }
}
