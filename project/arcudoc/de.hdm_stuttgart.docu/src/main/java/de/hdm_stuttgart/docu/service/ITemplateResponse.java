package de.hdm_stuttgart.docu.service;

import com.google.gson.JsonArray;

public interface ITemplateResponse {

    JsonArray getTemplate();

    String getTitle();

    void setTemplate(JsonArray template);

}
