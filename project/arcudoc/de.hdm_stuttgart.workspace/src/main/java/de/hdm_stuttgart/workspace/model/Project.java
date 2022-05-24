package de.hdm_stuttgart.workspace.model;

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
}
