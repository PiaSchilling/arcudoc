package de.hdm_stuttgart.workspace.service;

import de.hdm_stuttgart.workspace.model.ProjectMember;
import de.hdm_stuttgart.workspace.model.ProjectRequest;

import java.util.List;

public interface ICreateProject {

     void onCreateProjectClicked(String projectTitle,
                                 String projectDescription);

     String onAddMemberClicked(String memberMail, String jobLabel, String projectRole);

     List<String> getProjectRoles();
}
