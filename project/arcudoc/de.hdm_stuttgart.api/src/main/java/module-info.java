module de.hdm.stuttgart.api {

    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.guice;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires com.google.gson;
    requires okhttp3;
    requires okhttp3.logging;


    exports de.hdm_stuttgart.data.service;

    opens de.hdm_stuttgart.data.model to com.google.gson;
    exports de.hdm_stuttgart.data.api;
    exports de.hdm_stuttgart.data.model;

}