package de.hdm_stuttgart.navigation;

import de.hdm_stuttgart.ControllerFactory;
import de.hdm_stuttgart.Scenes;
import de.hdm_stuttgart.project.ProjectSceneController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the Navigation of the whole application
 * Each screen has a corresponding showScreen method
 */
public class NavigationController implements INavigationController{

    private static NavigationController INSTANCE;

    private static Stage stage;
    private static ControllerFactory controller;

    private NavigationController(){
        //singleton access
    }

    public static NavigationController getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new NavigationController();
            return INSTANCE;
        }
        return INSTANCE;
    }

    /**
     * initialize the navigation-controller
     * @param mainStage the stage object in which the scenes should be presented
     * @param controllerFactory the controller factory to set to the fxml controllers
     */
    public static void initNavigationController(Stage mainStage, ControllerFactory controllerFactory){
        stage = mainStage;
        controller = controllerFactory;
    }

    @Override
    public void showLoginScene() {
        try {
            final String fxmlFile = Scenes.LOGIN.getPath();
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controller);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 561, 584);
            Platform.runLater(() -> {
                stage.setScene(scene);
                stage.show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showWorkspaceScene() {
        try {
            final String fxmlFile = Scenes.WORKSPACE.getPath();
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controller);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1434, 800);
            Platform.runLater(() -> {
                stage.setScene(scene);
                stage.show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCreateProjectScene() {
        try {
            final String fxmlFile = Scenes.CREATE_PROJECT.getPath();
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controller);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1434, 800);
            Platform.runLater(() -> {
                stage.setScene(scene);
                stage.show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProjectScene(int projectId) {
        try{
            final String fxmlFile = Scenes.PROJECT.getPath();
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controller);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            ProjectSceneController controller = loader.getController();
            controller.setProjectId(projectId);
            final Scene scene = new Scene(rootNode, 1434, 800);
            Platform.runLater(() -> {
                stage.setScene(scene);
                stage.show();
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
