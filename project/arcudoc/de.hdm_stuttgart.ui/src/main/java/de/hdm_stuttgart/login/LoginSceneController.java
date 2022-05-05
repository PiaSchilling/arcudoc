package de.hdm_stuttgart.login;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hdm_stuttgart.login.integration.Login;
import de.hdm_stuttgart.login.service.ILogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ServiceLoader;

public class LoginSceneController {

    private final ILogin login;

    @FXML
    private Button gitlabButton;

    @FXML
    private Button googleButton;

    public LoginSceneController(ILogin login){
        this.login = login;

        Injector injector= Guice.createInjector(ServiceLoader.load(AbstractModule.class));
        login = injector.getInstance(Login.class);
    }

    @FXML
    void onLoginWithGitlabClicked(ActionEvent event) {
         login.onLoginWithGitLabButtonClicked();
    }

    @FXML
    void onLoginWithGoogleClicked(ActionEvent event) {
        login.onLoginWithGoogleButtonClicked();
    }

}
