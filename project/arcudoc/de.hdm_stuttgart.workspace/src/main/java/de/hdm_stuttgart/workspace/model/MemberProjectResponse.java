package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.data.api.ProfileRepository;
import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
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

    //info: getter for attributes of nested objects placed here that only IMemberProjectResponse has to be exposed to other modules

    @Override
    public String getProjectTitle() {
        return project.getTitle();
    }

    @Override
    public String getLastUpdated() {
        String formattedDate = new String();
        System.out.println("Date String: " + project.getLastUpdated());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{
            Date date = new Date(sdf.parse(project.getLastUpdated()).getTime());
            System.out.println(date);
            DateFormat dateFormat = new SimpleDateFormat("'last modified:' HH:mm 'Uhr' - dd.MM.yyyy");
            formattedDate = dateFormat.format(date);
            System.out.println(formattedDate);

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
    public int getProjectId() {
        return project.getProjectId();
    }
}
