package de.hdm_stuttgart.login;

import com.google.inject.Inject;
import de.hdm_stuttgart.login.service.ILogin;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginSceneController {

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
