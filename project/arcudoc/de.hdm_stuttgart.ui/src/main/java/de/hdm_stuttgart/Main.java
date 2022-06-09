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
    //private Stage stage;
   // private FXMLLoader loader;



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
        stage.setTitle("arcudoc");
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(controllerFactory);

        NavigationController.initNavigationController(stage,controllerFactory);
        //INavigationController navigationController = new NavigationController(controllerFactory,stage);//todo inject

        Preferences userPreferences = Preferences.userRoot().node("/arcudoc/profile");
        userPreferences.put("REFRESH_TOKEN","default");
        String auth = userPreferences.get("REFRESH_TOKEN","default");



        if(auth.equals("default")){
            NavigationController.getINSTANCE().showLoginScene();
            log.debug("Refresh token not found - Login required - show login scene");
        }else{
            NavigationController.getINSTANCE().showWorkspaceScene();
            log.debug("Refresh token found - No login required - show workspace scene");
        }

        //showLoginScene(stage,controllerFactory);
        //showWorkspaceScene(stage,controllerFactory);
        //showLoginScene(stage,controllerFactory);
        //showProjectScene(stage,controllerFactory);
        //showCreateProjectScene(stage,controllerFactory);
    }

   /* @Override
    public void showLoginScene(){
        try {
            final String fxmlFile = Scenes.LOGIN.getPath();
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(controllerFactory);
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 561, 584);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showWorkspaceScene(){
        try {
            final String fxmlFile = Scenes.WORKSPACE.getPath();
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1512, 800);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProjectScene(){
        try {
            final String fxmlFile = Scenes.PROJECT.getPath();
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1512, 800);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showCreateProjectScene(){
        try{
            final String fxmlFile = Scenes.CREATE_PROJECT.getPath();
            final Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            final Scene scene = new Scene(rootNode, 1512, 800);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

}
