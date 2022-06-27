module de.hdm.stuttgart.login {

    //exported packages
    exports de.hdm_stuttgart.login.service;
    exports de.hdm_stuttgart.login.guice;

    //module dependencies
    requires de.hdm.stuttgart.api;

    //external dependencies
    requires org.apache.logging.log4j;
    requires jdk.httpserver;
    requires com.google.guice;
    requires javafx.fxml;

    //exports to external modules
    exports de.hdm_stuttgart.login.integration to com.google.guice;

}