module de.hdm.stuttgart.docu {
    exports de.hdm_stuttgart.docu.service;
    exports de.hdm_stuttgart.docu.guice;

    opens de.hdm_stuttgart.docu.model to com.google.gson;



    requires com.google.guice;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.gson;
    requires okhttp3.logging;
    requires okhttp3;
    requires de.hdm.stuttgart.api;
    requires org.apache.logging.log4j;
    requires javafx.base;

    exports de.hdm_stuttgart.docu.data to com.google.guice;
    exports de.hdm_stuttgart.docu.integration to com.google.guice;
}