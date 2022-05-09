package de.hdm_stuttgart;

import com.google.inject.Inject;
import de.hdm_stuttgart.login.LoginSceneController;
import de.hdm_stuttgart.login.service.ILogin;
import javafx.application.Application;
import javafx.util.Callback;

public class ControllerFactory implements Callback {


    private final ILogin login;
    private final Application application;

    @Inject
    public ControllerFactory(ILogin login, Application application){
        this.login = login;
        this.application =  application;
    }

    @Override
    public Object call(Object param) {
        if(param == LoginSceneController.class){
            return new LoginSceneController(login,application);
        }
        return null;
    }
}
