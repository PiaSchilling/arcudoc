package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class InvitationResponse {

    private int project_id;
    private String member_mail;

    @SerializedName("projects")
    private Project project;

    public InvitationResponse(int project_id, String member_mail, Project project) {
        this.project_id = project_id;
        this.member_mail = member_mail;
        this.project = project;
    }

    public InvitationResponse() {
        //required by gson (retrofit)
    }

    @Override
    public String toString() {
        return "InvitationResponse{" +
                "project_id=" + project_id +
                ", member_mail='" + member_mail + '\'' +
                ", project=" + project +
                '}';
    }
}
