package de.hdm_stuttgart.docu.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.model.TemplateResponse;
import de.hdm_stuttgart.docu.service.IDocu;
import de.hdm_stuttgart.docu.service.IProject;
import de.hdm_stuttgart.docu.service.ITemplateResponse;

public class Docu implements IDocu {

    private final DocuController controller;

    @Inject
    public Docu(DocuController controller) {
        this.controller = controller;
    }

    @Override
    public IProject getProjectTitle() {
        return controller.getProjectTitle();
    }

    @Override
    public ITemplateResponse fetchTemplate(Integer projectID){

       return controller.getTemplateResponse(projectID);
    }
    @Override
    public void setContent(ITemplateResponse templateResponse){

        controller.setContent((TemplateResponse) templateResponse);
    }
}
