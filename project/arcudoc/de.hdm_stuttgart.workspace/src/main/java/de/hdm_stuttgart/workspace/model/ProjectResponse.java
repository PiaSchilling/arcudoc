package de.hdm_stuttgart.workspace.model;

public class ProjectResponse {

    private int id;
    private String title;

    public ProjectResponse(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public ProjectResponse() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
