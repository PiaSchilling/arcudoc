package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class MemberProjectResponse {

    @SerializedName("projects")
    private Project project;

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
                '}';
    }
}
