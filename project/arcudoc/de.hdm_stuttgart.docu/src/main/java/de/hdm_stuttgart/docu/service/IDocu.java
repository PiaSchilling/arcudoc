package de.hdm_stuttgart.docu.service;

import de.hdm_stuttgart.docu.model.TemplateResponse;

public interface IDocu {

    IProject getProjectTitle();

    ITemplateResponse fetchTemplate(Integer projectId);

    void setContent(ITemplateResponse templateResponse);
}
