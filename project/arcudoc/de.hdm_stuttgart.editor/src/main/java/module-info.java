module de.hdm.stuttgart.editor {

    requires com.google.guice;
    requires de.hdm.stuttgart.api;
    requires retrofit2;
    requires retrofit2.converter.gson;

    exports de.hdm_stuttgart.editor.service;
    exports de.hdm_stuttgart.editor.guice;
    exports de.hdm_stuttgart.editor.integration;

    opens de.hdm_stuttgart.editor.integration;

}