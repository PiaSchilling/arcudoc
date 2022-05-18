module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires com.google.guice;

    requires de.hdm.stuttgart.login;
    uses de.hdm_stuttgart.login.service.ILogin;
    uses com.google.inject.AbstractModule;

    opens de.hdm_stuttgart to javafx.fxml, javafx.graphics, javafx.controls;
    exports de.hdm_stuttgart.login to javafx.fxml, javafx.graphics, javafx.controls;
    opens de.hdm_stuttgart.login to javafx.fxml, javafx.graphics, javafx.controls;
    exports de.hdm_stuttgart;

    opens images;
    opens styles;
}