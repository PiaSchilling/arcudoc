package de.hdm_stuttgart.login;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.hdm_stuttgart.login.integration.Login;
import de.hdm_stuttgart.login.service.ILogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

public class LoginSceneController implements Initializable {

   // private final ILogin login;

    @FXML
    private Button gitlabButton;

    @FXML
    private Button googleButton;

    public LoginSceneController(){
      //  Injector injector= Guice.createInjector(ServiceLoader.load(AbstractModule.class));
       // login = injector.getInstance(Login.class);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //todo try to move logic connection from fxml to this init method

      /*  gitlabButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onLoginWithGitlabClicked(event);
            }
        });

        googleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onLoginWithGoogleClicked(event);
            }
        });*/
    }

    @FXML
    void onLoginWithGitlabClicked(ActionEvent event) {
        // login.onLoginWithGitLabButtonClicked();
        System.out.println("clicked gitlab");
    }

    @FXML
    void onLoginWithGoogleClicked(ActionEvent event) {
       // login.onLoginWithGoogleButtonClicked();
        System.out.println("clicked google");

    }


}
