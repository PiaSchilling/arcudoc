package de.hdm_stuttgart;

public enum Scenes {

    LOGIN("/fxml/test.fxml"),
    WORKSPACE("/fxml/workspace-screen.fxml"),
    PROJECT("/fxml/project-screen.fxml"),
    CREATE_PROJECT("/fxml/project-optionScreen.fxml"),
    INVITATION_CELL("/fxml/project-invitation-cell.fxml");

    private final String path;

    Scenes(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
