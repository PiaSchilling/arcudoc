package de.hdm_stuttgart.docu.model;

public class TemplateResponse {

    private final String[] templateDescription;

    public TemplateResponse(String[] templateDescription) {
        this.templateDescription = templateDescription;
    }

    public String[] getProjectTemplateText() {
        return templateDescription;
    }


}
