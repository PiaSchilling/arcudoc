package de.hdm_stuttgart.login.integration;

import de.hdm_stuttgart.login.model.AuthProvider;
import de.hdm_stuttgart.data.service.ApiConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);
    private final String authBaseUrl = ApiConstants.supabaseAuthUrl;

    private final PortListener portListener = new PortListener();

    public String getAuthUrl(AuthProvider provider) {
        return authBaseUrl + provider.toString().toLowerCase();
    }

    public void startAuthFlow(){
        portListener.startListener();
        log.debug("Authentication flow started");
    }

}
