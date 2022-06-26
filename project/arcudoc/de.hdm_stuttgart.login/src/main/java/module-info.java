module de.hdm.stuttgart.login {

    //exported modules
    exports de.hdm_stuttgart.login.service;
    exports de.hdm_stuttgart.login.guice;

    //external dependencies
    requires de.hdm.stuttgart.api;
    requires org.apache.logging.log4j;
    requires jdk.httpserver;
    requires com.google.guice;
    requires javafx.fxml;

    //exports to external modules
    exports de.hdm_stuttgart.login.integration to com.google.guice;

}