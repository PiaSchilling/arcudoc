package de.hdm_stuttgart.login.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.login.integration.Login;
import de.hdm_stuttgart.login.service.ILogin;

public class LoginGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ILogin.class).to(Login.class);
    }
}
