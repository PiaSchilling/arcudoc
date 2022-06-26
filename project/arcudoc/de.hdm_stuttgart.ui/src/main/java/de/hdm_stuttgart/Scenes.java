package de.hdm_stuttgart;

public enum Scenes {

    LOGIN("/fxml/test.fxml"),
    WORKSPACE("/fxml/workspace-screen.fxml"),
    PROJECT("/fxml/project-screen.fxml"),
    CREATE_PROJECT("/fxml/workspace-projectoption-screen.fxml"),
    INVITATION_CELL("/fxml/project-invitation-cell.fxml"),
    PROJECT_CELL("/fxml/project-cell.fxml"),
    USER_CELL("/fxml/addedUser-cell.fxml"),
    OWNER_CELL("/fxml/project-owner-cell.fxml");

    private final String path;

    Scenes(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
