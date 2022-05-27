package de.hdm_stuttgart;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hdm_stuttgart.docu.guice.DocuGuiceModule;
import de.hdm_stuttgart.editor.guice.EditorGuiceModule;
import de.hdm_stuttgart.login.guice.LoginGuiceModule;
import de.hdm_stuttgart.workspace.guice.WorkspaceGuiceModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Programm entry point
 */
public class Main extends Application {


    private static final Logger log = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting arcudoc");

        Injector injector = Guice.createInjector(
                new LoginGuiceModule(),
                new UiGuiceModule(),
                new WorkspaceGuiceModule(),
                new UiGuiceModule(),
                new EditorGuiceModule(),
                new DocuGuiceModule());
        ControllerFactory controllerFactory = injector.getInstance(ControllerFactory.class);

        //todo if user != logged in
       // showLoginScene(stage,controllerFactory);
        //showWorkspaceScene(stage,controllerFactory);
        //showLoginScene(stage,controllerFactory);
        //showProjectScene(stage,controllerFactory);
        showCreateProjectScene(stage,controllerFactory);
        //todo else show start screen

    }

    private void showLoginScene(Stage stage, ControllerFactory controllerFactory){
        try {
            final String fxmlFile = Scenes.LOGIN.getPath();
            final FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 561, 584);
            stage.setTitle("arcudoc");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWorkspaceScene(Stage stage, ControllerFactory controllerFactory){
        try {
            final String fxmlFile = Scenes.WORKSPACE.getPath();
            final FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 561, 584);
            stage.setTitle("arcudoc");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProjectScene(Stage stage, ControllerFactory controllerFactory){
        try {
            final String fxmlFile = Scenes.PROJECT.getPath();
            final FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1512, 800);
            stage.setTitle("arcudoc");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateProjectScene(Stage stage, ControllerFactory controllerFactory){
        try{
            final String fxmlFile = Scenes.CREATE_PROJECT.getPath();
            final FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1512, 800);
            stage.setTitle("arcudoc");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
