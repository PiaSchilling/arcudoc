package de.hdm_stuttgart.login.integration;

import de.hdm_stuttgart.data.service.NetworkStatus;
import de.hdm_stuttgart.login.model.AuthProvider;
import de.hdm_stuttgart.data.service.ApiConstants;
import javafx.beans.property.ObjectProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);
    private final String authBaseUrl = ApiConstants.SUPABASE_AUTH_URL_PROVIDER;

    private final PortListener portListener = new PortListener();

    public String getAuthUrl(AuthProvider provider) {
        return authBaseUrl + provider.toString().toLowerCase();
    }

    public void startAuthFlow(){
        portListener.startListener();
        log.debug("Authentication flow started");
    }

    public ObjectProperty<NetworkStatus> getAuthStatusProperty() {
        return portListener.getAuthStatusProperty();
    }

}
