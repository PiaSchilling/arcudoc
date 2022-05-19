module de.hdm.stuttgart.editor {

    requires com.google.guice;
    requires de.hdm.stuttgart.api;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires okhttp3;
    requires okhttp3.logging;
    requires javafx.fxml;
    requires javafx.base;

    exports de.hdm_stuttgart.editor.service;
    exports de.hdm_stuttgart.editor.guice;
    exports de.hdm_stuttgart.editor.integration;

    exports de.hdm_stuttgart.editor.data to com.google.guice;
    opens de.hdm_stuttgart.editor.model to com.google.gson;

    opens de.hdm_stuttgart.editor.integration;

}