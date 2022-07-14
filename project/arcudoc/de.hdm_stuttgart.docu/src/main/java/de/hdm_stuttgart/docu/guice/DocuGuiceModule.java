package de.hdm_stuttgart.docu.guice;

import com.google.inject.AbstractModule;
import de.hdm_stuttgart.docu.integration.Docu;
import de.hdm_stuttgart.docu.service.IDocu;

public class DocuGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IDocu.class).to(Docu.class);
    }
}
