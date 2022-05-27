package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.workspace.service.IInvitationResponse;

public class InvitationResponse implements IInvitationResponse {

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

    public int getProjectId() {
        return projectId;
    }

    public String getMemberMail() {
        return memberMail;
    }

    public Project getProject() {
        return project;
    }

    public String getJobLabel() {
        return jobLabel;
    }

    public String getProjectRole() {
        return projectRole;
    }

    @Override
    public String getProjectTitle() {
        return project.getTitle();
    }
}
