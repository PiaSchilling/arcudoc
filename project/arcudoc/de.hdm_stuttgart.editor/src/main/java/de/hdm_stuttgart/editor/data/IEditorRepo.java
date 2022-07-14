package de.hdm_stuttgart.editor.data;

import de.hdm_stuttgart.editor.model.HtmlResponse;

public interface IEditorRepo {
    void fetchMarkDown(String textToRender);
    HtmlResponse getMarkdownString();
}
