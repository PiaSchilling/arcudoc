package de.hdm_stuttgart;

import com.google.inject.AbstractModule;
import javafx.application.Application;

public class UiGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Application.class).to(Main.class);
    }
}
