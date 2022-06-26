package de.hdm_stuttgart.workspace;

import com.google.inject.Inject;
import de.hdm_stuttgart.navigation.NavigationController;
import de.hdm_stuttgart.workspace.userCell.UserCellComponent;
import de.hdm_stuttgart.workspace.service.ICreateProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class CreateProjectSceneController implements CreateProjectCellClickHandler{

    private final ICreateProject createProject;

    @Inject
    public CreateProjectSceneController(ICreateProject createProject) {
       this.createProject = createProject;
    }

    @FXML
    private Button addFileButton; //todo implement avatar choosing

    @FXML
    private Button addMemberButton;

    @FXML
    private ImageView backButton; //todo implement navigation

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
    private ImageView logo; //todo can be removed?

   @FXML
    private  VBox membersVBox;

    @FXML
    private ScrollPane memberListScrollPane;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Label error_description;

    @FXML
    private Label error_email;

    @FXML
    private Label error_job;

    @FXML
    private Label error_memberAlreadyAdded;

    @FXML
    private Label error_project;

    @FXML
    private Label error_role;


    public void initialize(){

        // fill project role comboBox
        ObservableList<String> projectRoles = FXCollections.observableArrayList();
        projectRoles.addAll(createProject.getProjectRoles());
        roleComboBox.setItems(projectRoles);

        addMemberButton.setOnAction(event -> onAddMemberClicked());
        createProjectButton.setOnAction(event -> onCreateProjectClicked());
        memberListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        memberListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    /**
     *  defines action for "create Project" click
     */
    private void onCreateProjectClicked(){
        System.out.println("create project clicked");
        String projectTitle = enterTitleTextField.getText();
        String projectDescription = enterDescriptionTextField.getText();

        if(projectTitle == null || projectTitle.isEmpty()){
            error_project.setText("Wie heißt dein Projekt? Gebe hier den Projektnamen an.");
        }else if(projectDescription == null || projectDescription.isEmpty()){
            error_description.setText("Was macht dein Projekt? Gebe hier eine Beschreibung an.");
        }else{
            createProject.onCreateProjectClicked(projectTitle,projectDescription);
            NavigationController.getINSTANCE().showWorkspaceScene();
        }
    }

    /**
     * defines action for "add Member" click
     */
    private void onAddMemberClicked(){
        System.out.println("Add member clicked");
        String memberMail = enterMailTextField.getText();
        String jobLabel = enterLabelTextField.getText();

        if(jobLabel == null || jobLabel.isEmpty()){
            error_job.setText("Welchen Job hat das Mitglied? Gebe hier ein Job Label an.");
        }else if(memberMail == null || memberMail.isEmpty()){
            error_email.setText("Welche Mail hat das Mitglied? Gebe hier die Mail an.");
        }else if(roleComboBox.getSelectionModel().isEmpty()){
            error_role.setText("Welche Rolle hat das Mitglied im Projekt? Wähle hier die Rolle aus.");
        }else{
            String projectRole = roleComboBox.getValue();
            String actionResponse = createProject.onAddMemberClicked(memberMail,jobLabel,projectRole);

            if(actionResponse.equals("Member added")){
                System.out.println(memberMail);
                UserCellComponent userCellComponent = new UserCellComponent(memberMail,this);
                membersVBox.getChildren().add(userCellComponent);
            }else{
               error_memberAlreadyAdded.setText("Ups, Mitglied wurde bereits hinzugefügt");
            }

        }
    }

    @Override
    public void onRemoveUserClicked() {
        //todo implement me
    }
}
