package de.hdm_stuttgart.workspace.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.workspace.integration.Workspace;
import de.hdm_stuttgart.workspace.service.IWorkspace;

public class WorkspaceGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IWorkspace.class).to(Workspace.class);
    }
}
