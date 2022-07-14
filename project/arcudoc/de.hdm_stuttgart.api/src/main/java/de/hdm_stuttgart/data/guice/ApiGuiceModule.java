package de.hdm_stuttgart.data.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.data.integration.ProfileRepository;
import de.hdm_stuttgart.data.service.IProfileRepository;

public class ApiGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IProfileRepository.class).to(ProfileRepository.class);
    }
}
