package de.hdm_stuttgart.editor.data;

import javafx.beans.property.StringProperty;

public interface IEditorRepo {
    void fetchMarkDown(String textToRender);
    StringProperty getHtmlStringProperty();
}
