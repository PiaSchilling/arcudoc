module workspace {
    exports de.hdm_stuttgart.workspace.service;
    exports de.hdm_stuttgart.workspace.guice;
    exports de.hdm_stuttgart.workspace.integration;

    requires de.hdm.stuttgart.api;

    requires com.google.guice;
    requires org.apache.logging.log4j;
    requires okhttp3;
    requires okhttp3.logging;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;

    opens de.hdm_stuttgart.workspace.model to com.google.gson;
    exports de.hdm_stuttgart.workspace.data to com.google.guice;


}