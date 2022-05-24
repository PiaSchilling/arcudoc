package de.hdm_stuttgart.workspace.model;

/**
 * Required by Retrofit
 * Used to model json body for createNewProject request
 */
public class ProjectRequest {

    private final String title;

    public ProjectRequest(String projectTitle) {
        this.title = projectTitle;
    }

    public String getProjectTitle() {
        return title;
    }
}
