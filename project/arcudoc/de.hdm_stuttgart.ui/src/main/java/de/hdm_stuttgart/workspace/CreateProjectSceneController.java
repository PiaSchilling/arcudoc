package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.workspace.service.ICreateProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class CreateProjectSceneController {

    private final ICreateProject createProject;

    @Inject
    public CreateProjectSceneController(ICreateProject createProject) {
       this.createProject = createProject;
    }

    @FXML
    private Button addFileButton;

    @FXML
    private Button addMemberButton;

    @FXML
    private ImageView backButton;

    @FXML
    private Button createProjectButton;

    @FXML
    private TextField enterDescriptionTextField;

    @FXML
    private TextField enterTitleTextField;

    @FXML
    private TextField enterLabelTextField;

    @FXML
    private TextField enterMailTextField;

    @FXML
    private ImageView logo;

    @FXML
    private ListView<String> memberList;

    @FXML
    private ComboBox<String> roleComboBox;

    public void initialize(){

        // fill project role comboBox
        ObservableList<String> projectRoles = FXCollections.observableArrayList();
        projectRoles.addAll(createProject.getProjectRoles()); //todo remove hardcoded strings
        roleComboBox.setItems(projectRoles);

        addMemberButton.setOnAction(event -> onAddMemberClicked());
        createProjectButton.setOnAction(event -> onCreateProjectClicked());

    }

    private void onCreateProjectClicked(){
        String projectTitle = enterTitleTextField.getText();
        String projectDescription = enterDescriptionTextField.getText();

        if(projectTitle == null || projectTitle.isEmpty()){
            //todo show error
        }else if(projectDescription == null || projectDescription.isEmpty()){
            //todo show error
        }else{
            createProject.onCreateProjectClicked(projectTitle,projectDescription);
        }
    }

    private void onAddMemberClicked(){

        String memberMail = enterMailTextField.getText();
        String jobLabel = enterLabelTextField.getText();

        if(jobLabel == null || jobLabel.isEmpty()){
            //todo show error
        }else if(memberMail == null || memberMail.isEmpty()){
            //todo show error
        }else if(roleComboBox.getSelectionModel().isEmpty()){
            //todo show error
        }else{
            String projectRole = roleComboBox.getValue();
            memberList.getItems().add(memberMail);
            String actionResponse = createProject.onAddMemberClicked(memberMail,jobLabel,projectRole);
            //todo show snackbar with actionResponse
        }


    }


}
