package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class ProjectMember {

    @SerializedName("member_mail")
    private String mail;

    @SerializedName("job_label")
    private String jobLabel;

    @SerializedName("project_role")
    private String projectRole;

    @SerializedName("project_id")
    private int projectId;

    public ProjectMember(String mail, String jobLabel, String projectRole, int projectId) {
        this.mail = mail;
        this.jobLabel = jobLabel;
        this.projectRole = projectRole;
        this.projectId = projectId;
    }

    public ProjectMember() {
    }

    public String getMail() {
        return mail;
    }

    public String getJobLabel() {
        return jobLabel;
    }

    public String getProjectRole() {
        return projectRole;
    }
}
