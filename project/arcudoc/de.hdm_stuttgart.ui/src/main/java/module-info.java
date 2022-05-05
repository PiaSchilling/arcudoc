module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires com.google.guice;

    requires de.hdm.stuttgart.login;
    uses de.hdm_stuttgart.login.service.ILogin;
    uses com.google.inject.AbstractModule;

    opens de.hdm_stuttgart to javafx.fxml;
    exports de.hdm_stuttgart.login to javafx.fxml;
    opens de.hdm_stuttgart.login to javafx.fxml;
    exports de.hdm_stuttgart;
}