package de.hdm_stuttgart.login.integration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.hdm_stuttgart.login.model.AuthProvider;
import de.hdm_stuttgart.data.service.ApiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;

public class LoginController {

    private static final Logger log = LogManager.getLogger();
    String authBaseUrl = ApiConstants.supabaseAuthUrl;

    public String getAuthUrl(AuthProvider provider) {
        switch (provider){
            case GITLAB -> {return authBaseUrl + "gitlab";}
            case GOOGLE -> {return authBaseUrl + "google";}
            default -> log.error("No such provider defined: " + provider);
        }
        return " "; //todo change impl
    }

    public void startHttpListener(){

    }

    static class AuthHttpHandler {

    }

}
