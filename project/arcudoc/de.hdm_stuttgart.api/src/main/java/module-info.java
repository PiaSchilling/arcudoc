module de.hdm.stuttgart.api {

    //exported modules
    exports de.hdm_stuttgart.data.service;

    //external dependencies
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires com.google.guice;
    requires org.apache.logging.log4j;
    requires com.google.gson;
    requires okhttp3;
    requires okhttp3.logging;
    requires java.prefs;

    //exports to external modules
    opens de.hdm_stuttgart.data.model to com.google.gson;


}
