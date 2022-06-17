package de.hdm_stuttgart.login.integration;

import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.login.model.AuthProvider;
import de.hdm_stuttgart.login.service.ILogin;
import javafx.beans.property.ObjectProperty;

public class Login implements ILogin {

    LoginController loginController = new LoginController();

    @Override
    public String onLoginWithGitLabButtonClicked() {
        loginController.startAuthFlow();
        return loginController.getAuthUrl(AuthProvider.GITLAB);
    }

    @Override
    public String onLoginWithGoogleButtonClicked() {
        loginController.startAuthFlow();
        return loginController.getAuthUrl(AuthProvider.GOOGLE);
    }

    @Override
    public ObjectProperty<NetworkStatus> getAuthStatusProperty() {
        return loginController.getAuthStatusProperty();
    }
}
