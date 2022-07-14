package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;

public class MemberProjectResponse implements IMemberProjectResponse {

    @SerializedName("projects")
    private Project project;

    @SerializedName("project_role")
    private String projectRole;

    public MemberProjectResponse(Project project) {
        this.project = project;
    }

    public MemberProjectResponse() {
    }

    public Project getProject() {
        return project;
    }

    @Override
    public String toString() {
        return "MemberProjectResponse{" +
                "project=" + project +
                ", projectRole='" + projectRole + '\'' +
                '}';
    }

    @Override
    public String getProjectRole() {
        return projectRole;
    }

    //info: getter for attributes of nested objects placed here that only IMemberProjectResponse has to be exposed to other modules

    @Override
    public String getProjectTitle() {
        return project.getTitle();
    }

    @Override
    public String getLastUpdated() {
        return project.getLastUpdated();
    }

    @Override
    public String getOwnerMail() {
        return project.getProjectOwner().getMail();
    }
}
