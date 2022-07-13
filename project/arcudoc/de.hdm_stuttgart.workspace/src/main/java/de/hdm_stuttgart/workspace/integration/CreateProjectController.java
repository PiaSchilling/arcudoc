package de.hdm_stuttgart.workspace.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.data.IWorkspaceRepo;
import de.hdm_stuttgart.workspace.model.ProjectMember;
import de.hdm_stuttgart.workspace.model.ProjectRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateProjectController {

    private final IWorkspaceRepo workspaceRepo;
    private final List<ProjectMember> projectMembers;

    private static final Logger log = LogManager.getLogger(CreateProjectController.class);

    @Inject
    public CreateProjectController(IWorkspaceRepo workspaceRepo) {
        this.workspaceRepo = workspaceRepo;
        this.projectMembers = new ArrayList<>();
    }

    /**
     * call workspace repo to create a project
     * @param projectTitle the title of the project provided by the user
     * @param projectDescription the description of the project provided by the user
     */
    public void createProject(String projectTitle, String projectDescription){
        workspaceRepo.createProject(projectTitle,projectMembers);
        log.debug("Create project with title: " + projectTitle + " and members: " + Arrays.toString(projectMembers.toArray()));
    }

    /**
     * add a projectMember to the projectMembers list
     * @param memberMail the mail of the member which should be added
     * @param jobLabel the jobLabel the member should have
     * @param projectRole the projectRole the member should have
     * @return a String indicating if the adding was successful (duplicate adding fails)
     */
    public String addProjectMember(String memberMail, String jobLabel, String projectRole) {
        ProjectMember projectMember = new ProjectMember(memberMail,jobLabel,projectRole);

        if(projectMembers.stream().noneMatch(member -> member.getMail().equals(memberMail))){
            projectMembers.add(projectMember); //only add member if not already added
            log.debug("Added member: " + projectMember);
            return "Member added";
        }
        return "Member "+ memberMail + " already added";
    }

    public List<String> getProjectRoles(){
        return Arrays.stream(ProjectRole.values()).map(ProjectRole::getSupabaseName).toList();
    }


}
