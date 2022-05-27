package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;

public class Project {

    private String title;

    public Project(String title) {
        this.title = title;
    }

    public Project() {
        //required by gson (retrofit)
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }
}
