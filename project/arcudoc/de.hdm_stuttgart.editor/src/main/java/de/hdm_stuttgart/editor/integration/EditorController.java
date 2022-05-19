package de.hdm_stuttgart.editor.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.data.ChangeListener;
import de.hdm_stuttgart.editor.data.IEditorRepo;
import de.hdm_stuttgart.editor.model.HtmlResponse;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditorController implements ChangeListener {

    private IEditorRepo editorRepo;
    private final StringProperty htmlStringProperty;

    @Inject
    public EditorController(IEditorRepo editorRepo) {
        this.editorRepo = editorRepo;
        htmlStringProperty = new SimpleStringProperty("empty");
    }

    public void renderMarkdownToHtml(String textToRender){
        editorRepo.fetchMarkDown(textToRender);
    }

    @Override
    public void htmlReceived(HtmlResponse html) {
        System.out.println(html.getHtml());
        htmlStringProperty.setValue(html.getHtml());
    }

    public StringProperty getHtmlStringProperty() {
        return htmlStringProperty;
    }
}
