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

    public ProjectMember(String mail, String jobLabel, String projectRole) {
        this.mail = mail;
        this.jobLabel = jobLabel;
        this.projectRole = projectRole;
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

    @Override
    public String toString() {
        return "ProjectMember{" +
                "mail='" + mail + '\'' +
                ", jobLabel='" + jobLabel + '\'' +
                ", projectRole='" + projectRole + '\'' +
                '}';
    }
}
