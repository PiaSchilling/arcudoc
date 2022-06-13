package de.hdm_stuttgart.docu.service;

import com.google.gson.JsonArray;

import java.util.List;

public interface ITemplateResponse {

    JsonArray getTemplate();

    String getTitle();

}
