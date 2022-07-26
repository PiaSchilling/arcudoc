package de.hdm_stuttgart;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.service.IDocu;
import de.hdm_stuttgart.editor.service.IEditor;
import de.hdm_stuttgart.login.LoginSceneController;
import de.hdm_stuttgart.login.service.ILogin;
import de.hdm_stuttgart.project.ProjectSceneController;
import de.hdm_stuttgart.workspace.CreateProjectSceneController;
import de.hdm_stuttgart.workspace.WorkspaceSceneController;
import de.hdm_stuttgart.workspace.service.ICreateProject;
import de.hdm_stuttgart.workspace.service.IWorkspace;
import javafx.application.Application;
import javafx.util.Callback;

/**
/**
 * class defines how controller classes should be instanced by the fxml loader
 */
public class ControllerFactory implements Callback<Class<?>, Object> {


    private final ILogin login;
    private final IEditor editor;
    private final IWorkspace workspace;
    private final IDocu docu;
    private final ICreateProject createProject;

    private final Application application;

    @Inject
    public ControllerFactory(ILogin login, Application application, IEditor editor, IWorkspace workspace, IDocu docu, ICreateProject createProject){
        this.login = login;
        this.editor = editor;
        this.application =  application;
        this.workspace = workspace;
        this.docu = docu;
        this.createProject = createProject;
    }

    @Override
    public Object call(Class<?> param) {
        if(param == LoginSceneController.class){
            return new LoginSceneController(login,application);
        }else if(param == ProjectSceneController.class){
            return new ProjectSceneController(editor,docu);
        }else if(param == WorkspaceSceneController.class){
            return new WorkspaceSceneController(workspace);
        }else if(param == CreateProjectSceneController.class){
            return new CreateProjectSceneController(createProject);
        }
        return null;
    }
}
