package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class MemberRequest {

    @SerializedName("project_id")
    private int projectId;

    @SerializedName("job_label")
    private String jobLabel;

    @SerializedName("project_role")
    private String projectRole;

    /**
     * @param projectId the if of the project the members requests
     */
    public MemberRequest(int projectId, ProjectMember projectMember) {
        this.projectId = projectId;
        this.jobLabel = projectMember.getJobLabel();
        this.projectRole = projectMember.getProjectRole();
    }

    public MemberRequest() {
        //required by gson (retrofit)
    }

    public int getProjectId() {
        return projectId;
    }
}
