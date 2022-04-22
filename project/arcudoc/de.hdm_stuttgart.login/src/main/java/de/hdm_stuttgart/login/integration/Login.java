package de.hdm_stuttgart.login.integration;

import de.hdm_stuttgart.login.model.AuthProvider;
import de.hdm_stuttgart.login.service.ILogin;

public class Login implements ILogin {

    LoginController loginController = new LoginController();

    @Override
    public String onLoginWithGitLabButtonClicked() {
        return loginController.getAuthUrl(AuthProvider.GITLAB);
    }

    @Override
    public String onLoginWithGoogleButtonClicked() {
        return loginController.getAuthUrl(AuthProvider.GOOGLE);
    }
}
