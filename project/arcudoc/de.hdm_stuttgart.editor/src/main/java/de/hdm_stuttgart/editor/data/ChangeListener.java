package de.hdm_stuttgart.editor.data;

import de.hdm_stuttgart.editor.model.HtmlResponse;

public interface ChangeListener {

    void htmlReceived(HtmlResponse html);
}
