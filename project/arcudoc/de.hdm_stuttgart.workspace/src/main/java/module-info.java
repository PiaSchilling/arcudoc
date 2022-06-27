module de.hdm.stuttgart.workspace {

    //exported packages
    exports de.hdm_stuttgart.workspace.service;
    exports de.hdm_stuttgart.workspace.guice;

    //external dependencies
    requires com.google.guice;
    requires org.apache.logging.log4j;
    requires okhttp3;
    requires okhttp3.logging;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.base;

    //module dependencies
    requires de.hdm.stuttgart.api;
    requires annotations;

    //exports to external modules
    exports de.hdm_stuttgart.workspace.data to com.google.guice;
    exports de.hdm_stuttgart.workspace.integration to com.google.guice;
    opens de.hdm_stuttgart.workspace.model to com.google.gson;


}