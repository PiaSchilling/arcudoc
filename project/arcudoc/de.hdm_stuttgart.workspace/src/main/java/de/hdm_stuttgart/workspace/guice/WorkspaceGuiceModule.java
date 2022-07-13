package de.hdm_stuttgart.workspace.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.workspace.data.IWorkspaceRepo;
import de.hdm_stuttgart.workspace.data.WorkspaceRepo;
import de.hdm_stuttgart.workspace.integration.CreateProject;
import de.hdm_stuttgart.workspace.integration.Workspace;
import de.hdm_stuttgart.workspace.service.ICreateProject;
import de.hdm_stuttgart.workspace.service.IWorkspace;

public class WorkspaceGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IWorkspace.class).to(Workspace.class);
        bind(ICreateProject.class).to(CreateProject.class);
        bind(IWorkspaceRepo.class).to(WorkspaceRepo.class);
    }
}
