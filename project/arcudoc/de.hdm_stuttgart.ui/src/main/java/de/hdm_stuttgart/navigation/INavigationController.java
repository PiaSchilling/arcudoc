package de.hdm_stuttgart.navigation;

import javafx.stage.Stage;

public interface INavigationController {

    void showLoginScene();
    void showWorkspaceScene();
    void showCreateProjectScene();
    void showProjectScene(int projectId);
}
