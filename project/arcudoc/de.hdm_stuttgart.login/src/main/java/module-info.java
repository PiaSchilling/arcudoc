module de.hdm.stuttgart.login {
    exports de.hdm_stuttgart.login.service;

    requires de.hdm.stuttgart.api;
    requires org.apache.logging.log4j;
    requires jdk.httpserver;
    requires com.google.guice;

    //provides de.hdm_stuttgart.login.service.ILogin with de.hdm_stuttgart.login.integration.Login;
    provides com.google.inject.AbstractModule with de.hdm_stuttgart.login.guice.LoginGuiceModule;
    opens de.hdm_stuttgart.login.integration;
    exports de.hdm_stuttgart.login.integration;
}