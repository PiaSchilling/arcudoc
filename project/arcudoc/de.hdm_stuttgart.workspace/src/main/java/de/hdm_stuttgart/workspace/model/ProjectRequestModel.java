package de.hdm_stuttgart.workspace.model;

/**
 * Required by Retrofit
 * Used to model json body for createNewProject request
 */
public class ProjectRequestModel {

    private final String title;

    public ProjectRequestModel(String projectTitle) {
        this.title = projectTitle;
    }

    public String getProjectTitle() {
        return title;
    }
}
