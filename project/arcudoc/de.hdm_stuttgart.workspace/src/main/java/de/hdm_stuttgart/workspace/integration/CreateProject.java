package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.model.ProjectMember;
import de.hdm_stuttgart.workspace.service.ICreateProject;

import java.util.List;

public class CreateProject implements ICreateProject {

    private final CreateProjectController createProjectController;

    @Inject
    public CreateProject(CreateProjectController createProjectController) {
        this.createProjectController = createProjectController;
    }

    @Override
    public void onCreateProjectClicked(String projectTitle, String projectDescription) {
        createProjectController.createProject(projectTitle,projectDescription);
    }

    public String onAddMemberClicked(String memberMail, String jobLabel, String projectRole){
         return createProjectController.addProjectMember(memberMail,jobLabel,projectRole);
    }

    @Override
    public List<String> getProjectRoles() {
        return createProjectController.getProjectRoles();
    }

}
