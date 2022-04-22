module de.hdm.stuttgart.api {

    requires retrofit2;
    requires retrofit2.converter.gson;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    exports de.hdm_stuttgart.data.service;
}