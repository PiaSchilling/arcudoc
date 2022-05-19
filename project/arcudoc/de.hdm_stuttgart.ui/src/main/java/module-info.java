module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires de.hdm.stuttgart.login;
    requires de.hdm.stuttgart.docu;

    opens de.hdm_stuttgart to javafx.fxml;
    exports de.hdm_stuttgart;
}