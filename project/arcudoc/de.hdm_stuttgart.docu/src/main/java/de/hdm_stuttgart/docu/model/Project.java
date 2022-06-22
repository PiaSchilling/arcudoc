package de.hdm_stuttgart.docu.model;

import com.google.gson.annotations.SerializedName;

public class Project implements de.hdm_stuttgart.docu.service.IProject {

    @SerializedName("title")
    private String projectTitle;


    public Project(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Project() {

    }

    @Override
    public String getProjectTitle() {
        return projectTitle;
    }
}
