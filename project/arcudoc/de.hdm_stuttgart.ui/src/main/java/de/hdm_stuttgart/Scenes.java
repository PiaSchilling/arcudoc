package de.hdm_stuttgart;

public enum Scenes {

    LOGIN("/fxml/test.fxml"),
    PROJECT("/fxml/project-screen.fxml");

    private final String path;

    Scenes(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
