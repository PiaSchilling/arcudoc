package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class InvitationResponse {

    private int projectId;
    private String invitationMail;

    @SerializedName("projects")
    private Project project;

    public InvitationResponse(int projectId, String invitationMail, Project project) {
        this.projectId = projectId;
        this.invitationMail = invitationMail;
        this.project = project;
    }

    public InvitationResponse() {
        //required by gson (retrofit)
    }

    @Override
    public String toString() {
        return "InvitationResponse{" +
                "projectId=" + projectId +
                ", invitationMail='" + invitationMail + '\'' +
                ", project=" + project +
                '}';
    }
}
