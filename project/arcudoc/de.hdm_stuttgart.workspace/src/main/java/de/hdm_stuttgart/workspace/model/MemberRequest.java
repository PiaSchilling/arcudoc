package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class MemberRequest {

    @SerializedName("project_id")
    private int projectId;

    /**
     * @param projectId the if of the project the members requests
     */
    public MemberRequest(int projectId) {
        this.projectId = projectId;
    }

    public MemberRequest() {
        //required by gson (retrofit)
    }

    public int getProjectId() {
        return projectId;
    }
}
