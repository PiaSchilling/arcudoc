module de.hdm.stuttgart.editor {

    //external dependencies
    requires com.google.guice;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires okhttp3;
    requires okhttp3.logging;
    requires javafx.fxml;
    requires javafx.base;
    requires org.apache.logging.log4j;
    requires annotations;

    //module dependencies
    requires de.hdm.stuttgart.api;

    //exported modules
    exports de.hdm_stuttgart.editor.service;
    exports de.hdm_stuttgart.editor.guice;

    //exports to external modules
    exports de.hdm_stuttgart.editor.data to com.google.guice;
    exports de.hdm_stuttgart.editor.integration to com.google.guice;
    opens de.hdm_stuttgart.editor.model to com.google.gson;


}