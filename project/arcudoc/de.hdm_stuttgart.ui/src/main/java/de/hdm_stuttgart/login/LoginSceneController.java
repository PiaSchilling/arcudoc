package de.hdm_stuttgart.login;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import de.hdm_stuttgart.login.integration.Login;
import de.hdm_stuttgart.login.service.ILogin;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

public class LoginSceneController {

    private final Application application; //
    private final ILogin login;

    @FXML
    private Button gitlabButton;

    @FXML
    private Button googleButton;

    @Inject
    public LoginSceneController(ILogin login, Application application) {
        //  Injector injector= Guice.createInjector(ServiceLoader.load(AbstractModule.class));
        // login = injector.getInstance(Login.class);

        this.login = login;
        this.application = application;

    }

    public void initialize() {
        System.out.println("Init called");

        gitlabButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onLoginWithGitlabClicked();
            }
        });

        googleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onLoginWithGoogleClicked();
            }
        });
    }


    void onLoginWithGitlabClicked() {
        System.out.println("clicked gitlab");
        String test =  login.onLoginWithGitLabButtonClicked();
        System.out.println(test);
        application.getHostServices().showDocument(test);

    }


    void onLoginWithGoogleClicked() {
        System.out.println("clicked google");
        String test = login.onLoginWithGoogleButtonClicked();
        System.out.println(test);
        application.getHostServices().showDocument(test);
    }



}
