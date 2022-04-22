package de.hdm_stuttgart.login;

import de.hdm_stuttgart.login.service.ILogin;

public class LoginController {

    private final ILogin login;

    public LoginController(ILogin login){
        this.login = login;
    }

    private void onLoginWithGitlabClicked(){
        login.onLoginWithGitLabButtonClicked();
    }
}
