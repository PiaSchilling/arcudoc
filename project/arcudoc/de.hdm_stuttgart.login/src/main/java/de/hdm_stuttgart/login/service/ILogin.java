package de.hdm_stuttgart.login.service;

import de.hdm_stuttgart.data.service.NetworkStatus;
import javafx.beans.property.ObjectProperty;

public interface ILogin {

    String onLoginWithGitLabButtonClicked();
    String onLoginWithGoogleButtonClicked();
    ObjectProperty<NetworkStatus> getAuthStatusProperty();
}
