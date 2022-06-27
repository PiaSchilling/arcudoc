package de.hdm_stuttgart;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hdm_stuttgart.docu.guice.DocuGuiceModule;
import de.hdm_stuttgart.editor.guice.EditorGuiceModule;
import de.hdm_stuttgart.login.guice.LoginGuiceModule;
import de.hdm_stuttgart.navigation.INavigationController;
import de.hdm_stuttgart.navigation.NavigationController;
import de.hdm_stuttgart.workspace.guice.WorkspaceGuiceModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * Programm entry point
 */
public class Main extends Application{


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
                new EditorGuiceModule(),
                new DocuGuiceModule());

        ControllerFactory controllerFactory = injector.getInstance(ControllerFactory.class);
        stage.setTitle("arcudoc");
        NavigationController.initNavigationController(stage,controllerFactory);

        Preferences userPreferences = Preferences.userRoot().node("/arcudoc/profile");
        userPreferences.put("REFRESH_TOKEN","default"); //todo remove once done with testing
        String auth = userPreferences.get("REFRESH_TOKEN","default");

        if(auth.equals("default")){
            NavigationController.getINSTANCE().showLoginScene();
            log.debug("Refresh token not found - Login required - show login scene");
        }else{
            NavigationController.getINSTANCE().showWorkspaceScene();
            log.debug("Refresh token found - No login required - show workspace scene");
        }

    }


}
