package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberProjectResponse implements IMemberProjectResponse {
    private static final Logger log = LogManager.getLogger(MemberProjectResponse.class);


    @SerializedName("projects")
    private Project project;

    @SerializedName("project_role")
    private String projectRole;

    @SerializedName("job_label")
    private String jobLabel;

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
                ", projectRole='" + projectRole + '\'' +
                '}';
    }

    @Override
    public String getProjectRole() {
        return projectRole;
    }

    @Override
    public String getJobLabel() {
        System.out.println(jobLabel);

        if (jobLabel == null){
            return "Team-Admin";
        }
        return jobLabel;
    }

    //info: getter for attributes of nested objects placed here that only IMemberProjectResponse has to be exposed to other modules

    @Override
    public String getProjectTitle() {
        return project.getTitle();
    }

    @Override
    public String getLastUpdated() {
        String formattedDate = new String();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{
            Date date = new Date(sdf.parse(project.getLastUpdated()).getTime());
            DateFormat dateFormat = new SimpleDateFormat("HH:mm 'Uhr' - dd.MM.yyyy");
            formattedDate = dateFormat.format(date);

        } catch (ParseException e) {
            log.error("Failed parsing project creation date " + e.getMessage());
        }

        return formattedDate;
    }

    @Override
    public String getOwnerMail() {
        return project.getProjectOwner().getMail();
    }

    @Override
    public String getOwnerName() {
        return project.getProjectOwner().getUserName();
    }

    @Override
    public int getProjectId() {
        return project.getProjectId();
    }
}
