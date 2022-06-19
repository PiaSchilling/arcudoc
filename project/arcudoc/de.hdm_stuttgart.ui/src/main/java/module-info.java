module gui {

    //module dependencies
    requires de.hdm.stuttgart.login;
    requires workspace;
    requires de.hdm.stuttgart.editor;
    requires de.hdm.stuttgart.docu;
    requires de.hdm.stuttgart.api;

    //external dependencies
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.apache.logging.log4j;
    requires com.google.guice;
    requires java.prefs;
    requires com.google.gson;

    uses com.google.inject.AbstractModule;

    opens de.hdm_stuttgart to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.login to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.workspace to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.project to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.navigation to javafx.controls, javafx.fxml, javafx.graphics;
    exports de.hdm_stuttgart to com.google.guice;
    exports de.hdm_stuttgart.navigation;

    opens images;
    opens styles;
}