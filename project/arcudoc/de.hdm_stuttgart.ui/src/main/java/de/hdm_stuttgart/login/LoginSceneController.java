package de.hdm_stuttgart.login;

import com.google.inject.Inject;
import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.login.service.ILogin;

import de.hdm_stuttgart.navigation.NavigationController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginSceneController {

    private static final Logger log = LogManager.getLogger(LoginSceneController.class);

    private final Application application; //needed to be able to open the browser
    private final ILogin login;

    @FXML
    private Button gitlabButton;

    @FXML
    private Button googleButton;


    @Inject
    public LoginSceneController(ILogin login, Application application) {
        this.login = login;
        this.application = application;
    }

    public void initialize() {
        gitlabButton.setOnAction(event -> onLoginWithGitlabClicked());
        googleButton.setOnAction(event -> onLoginWithGoogleClicked());

        login.getAuthStatusProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == NetworkStatus.AUTH_SUCCESS){
                NavigationController.getINSTANCE().showWorkspaceScene();
                log.debug("Login finished, showing workspace screen");
            }else{
                NavigationController.getINSTANCE().showLoginScene();
                log.error("Login failed, showing login screen");
            }
        });
    }


    void onLoginWithGitlabClicked() {
        String loginUrl =  login.onLoginWithGitLabButtonClicked();
        application.getHostServices().showDocument(loginUrl);
    }

    void onLoginWithGoogleClicked() {
        String loginUrl = login.onLoginWithGoogleButtonClicked();
        application.getHostServices().showDocument(loginUrl);
    }

}
