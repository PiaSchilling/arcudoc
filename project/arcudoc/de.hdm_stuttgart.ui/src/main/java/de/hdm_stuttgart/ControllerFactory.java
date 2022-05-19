package de.hdm_stuttgart;

import com.google.inject.Inject;
import de.hdm_stuttgart.editor.service.IEditor;
import de.hdm_stuttgart.login.LoginSceneController;
import de.hdm_stuttgart.login.service.ILogin;
import de.hdm_stuttgart.project.ProjectSceneController;
import javafx.application.Application;
import javafx.util.Callback;

/**
/**
 * class defines how controller classes should be instanced by the fxml loader
 */
public class ControllerFactory implements Callback<Class<?>, Object> {


    private final ILogin login;
    private final IEditor editor;
    private final Application application;

    @Inject
    public ControllerFactory(ILogin login, Application application, IEditor editor){
        this.login = login;
        this.editor = editor;
        this.application =  application;
    }

    @Override
    public Object call(Class<?> param) {
        if(param == LoginSceneController.class){
            return new LoginSceneController(login,application);
        }else if(param == ProjectSceneController.class){
            return new ProjectSceneController(editor);
        }
        return null;
    }
}
