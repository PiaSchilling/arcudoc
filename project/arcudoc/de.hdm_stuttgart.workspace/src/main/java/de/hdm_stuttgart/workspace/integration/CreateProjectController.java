package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.data.WorkspaceRepo;
import de.hdm_stuttgart.workspace.model.ProjectMember;
import de.hdm_stuttgart.workspace.model.ProjectRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateProjectController {

    private final WorkspaceRepo workspaceRepo;
    private final List<ProjectMember> projectMembers;

    private static final Logger log = LogManager.getLogger(CreateProjectController.class);

    @Inject
    public CreateProjectController(WorkspaceRepo workspaceRepo) {
        this.workspaceRepo = workspaceRepo;
        this.projectMembers = new ArrayList<>();
    }

    public void createProject(String projectTitle, String projectDescription){
        workspaceRepo.createProject(projectTitle,projectMembers);
        log.debug("Create project with title: " + projectTitle + " and members: " + Arrays.toString(projectMembers.toArray()));
    }

    public void addProjectMember(String memberMail, String jobLabel, String projectRole) {
        ProjectMember projectMember = new ProjectMember(memberMail,jobLabel,projectRole);
        projectMembers.add(projectMember);
        log.debug("Adding member: " + projectMember);

    }

    public List<String> getProjectRoles(){
        List<String> roleStrings = Arrays.stream(ProjectRole.values()).map(ProjectRole::getSupabaseName).toList();
        System.out.println(roleStrings);
        return roleStrings;
    }


}
