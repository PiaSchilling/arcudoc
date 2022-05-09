package de.hdm_stuttgart;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hdm_stuttgart.login.LoginSceneController;
import de.hdm_stuttgart.login.guice.LoginGuiceModule;
import de.hdm_stuttgart.login.service.ILogin;
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

        Injector injector = Guice.createInjector(
                new LoginGuiceModule(),
                new UiGuiceModule());

        ControllerFactory controllerFactory = injector.getInstance(ControllerFactory.class);
        log.info("Starting arcudoc");

        final String fxmlFile = "/fxml/login-test.fxml";
        final FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(controllerFactory);
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        final Scene scene = new Scene(rootNode, 561, 584);
        stage.setTitle("arcudoc");
        stage.setScene(scene);
        stage.show();

    }
}
