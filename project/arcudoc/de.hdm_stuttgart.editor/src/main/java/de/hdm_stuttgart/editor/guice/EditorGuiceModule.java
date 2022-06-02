package de.hdm_stuttgart.editor.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.editor.data.ChangeListener;
import de.hdm_stuttgart.editor.data.EditorRepo;
import de.hdm_stuttgart.editor.data.IEditorRepo;
import de.hdm_stuttgart.editor.integration.Editor;
import de.hdm_stuttgart.editor.integration.EditorController;
import de.hdm_stuttgart.editor.service.IEditor;

public class EditorGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IEditor.class).to(Editor.class);
        bind(IEditorRepo.class).to(EditorRepo.class);
        bind(ChangeListener.class).to(EditorController.class);
    }
}
