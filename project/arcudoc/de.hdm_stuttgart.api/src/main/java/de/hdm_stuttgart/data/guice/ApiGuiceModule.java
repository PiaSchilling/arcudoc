package de.hdm_stuttgart.data.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.data.api.ProfileRepository;
import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.api.SupabaseAuthClient;
import de.hdm_stuttgart.data.service.IProfileRepository;
import retrofit2.Retrofit;

public class ApiGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IProfileRepository.class).to(ProfileRepository.class);
    }
}
