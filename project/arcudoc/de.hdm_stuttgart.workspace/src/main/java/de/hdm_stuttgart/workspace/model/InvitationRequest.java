package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class InvitationRequest {


    @SerializedName("project_id")
    private int projectId;

    @SerializedName("member_mail")
    private String mail;

    @SerializedName("job_label")
    private String jobLabel;

    @SerializedName("project_role")
    private String projectRole;

    /**
     * @param projectId the if of the project the members requests
     */
    public InvitationRequest(int projectId, ProjectMember projectMember) {
        this.projectId = projectId;
        this.mail = projectMember.getMail();
        this.jobLabel = projectMember.getJobLabel();
        this.projectRole = projectMember.getProjectRole();
    }

    public InvitationRequest() {
        //required by retrofit
    }
}
