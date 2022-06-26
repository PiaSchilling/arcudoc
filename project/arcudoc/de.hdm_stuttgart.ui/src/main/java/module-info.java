module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.apache.logging.log4j;
    requires com.google.guice;

    requires de.hdm.stuttgart.login;
    requires workspace;
    requires de.hdm.stuttgart.editor;
    uses de.hdm_stuttgart.login.service.ILogin;
    requires de.hdm.stuttgart.docu;
    requires java.prefs;
    requires de.hdm.stuttgart.api;
    requires com.google.gson;

    uses com.google.inject.AbstractModule;

    opens de.hdm_stuttgart to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.login to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.workspace to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.project to javafx.fxml, javafx.graphics, javafx.controls;


    exports de.hdm_stuttgart.login to javafx.fxml, javafx.graphics, javafx.controls;
    exports de.hdm_stuttgart;

    opens images;
    opens styles;
    exports de.hdm_stuttgart.navigation;
    opens de.hdm_stuttgart.navigation to javafx.controls, javafx.fxml, javafx.graphics;
    opens de.hdm_stuttgart.workspace.invitationCell to javafx.controls, javafx.fxml, javafx.graphics;
    opens de.hdm_stuttgart.workspace.ownerCell to javafx.controls, javafx.fxml, javafx.graphics;
    opens de.hdm_stuttgart.workspace.userCell to javafx.controls, javafx.fxml, javafx.graphics;
    opens de.hdm_stuttgart.workspace.projectCell to javafx.controls, javafx.fxml, javafx.graphics;
}