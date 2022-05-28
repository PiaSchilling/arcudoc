package de.hdm_stuttgart.workspace.model;

import com.google.gson.annotations.SerializedName;


public class Project {

    private String title;

    @SerializedName("last_updated")
    private String lastUpdated; //todo this should be an Instant or LocalDateTime (unfortunately not possible with retrofit at the moment)

    @SerializedName("profiles")
    private Profile projectOwner;

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
                ", lastUpdated=" + lastUpdated +
                ", projectOwner=" + projectOwner +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Profile getProjectOwner() {
        return projectOwner;
    }
}
