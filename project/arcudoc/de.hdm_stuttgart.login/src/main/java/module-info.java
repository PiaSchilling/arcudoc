module de.hdm.stuttgart.login {

    requires de.hdm.stuttgart.api;
    requires org.apache.logging.log4j;
    requires jdk.httpserver;

    exports de.hdm_stuttgart.login.service;
}