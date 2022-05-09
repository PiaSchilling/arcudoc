package de.hdm_stuttgart;

import de.hdm_stuttgart.login.LoginSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        final String fxmlFile = "/fxml/login-scene.fxml";
        final FXMLLoader loader = new FXMLLoader();
        //LoginSceneController sceneController = new LoginSceneController();
        //loader.setController(sceneController);
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        final Scene scene = new Scene(rootNode, 561, 584);
        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
}
