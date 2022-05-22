package de.hdm_stuttgart;

public enum Scenes {

    LOGIN("/fxml/test.fxml"),
    WORKSPACE("/fxml/workspace-screen.fxml");

    private final String path;

    Scenes(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
