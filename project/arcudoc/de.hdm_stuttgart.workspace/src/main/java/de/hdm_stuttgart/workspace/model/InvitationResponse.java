package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class InvitationResponse {

    @SerializedName("project_id")
    private int projectId;

    @SerializedName("member_mail")
    private String memberMail;

    @SerializedName("projects")
    private Project project;

    @SerializedName("job_label")
    private String jobLabel;

    @SerializedName("project_role")
    private String projectRole;

    public InvitationResponse(int projectId, String memberMail, Project project) {
        this.projectId = projectId;
        this.memberMail = memberMail;
        this.project = project;
    }

    public InvitationResponse() {
        //required by gson (retrofit)
    }

    @Override
    public String toString() {
        return "InvitationResponse{" +
                "projectId=" + projectId +
                ", memberMail='" + memberMail + '\'' +
                ", project=" + project +
                '}';
    }

}
