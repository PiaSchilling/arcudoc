package de.hdm_stuttgart.docu.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.docu.service.ITemplateResponse;

import java.util.List;

public class TemplateResponse implements ITemplateResponse {

    @SerializedName("content")
    private JsonArray template;

    @SerializedName("title")
    private String title;


    public TemplateResponse(JsonArray template, String title){
        this.template = template;
        this.title = title;
    }



    public TemplateResponse() {

    }


    @Override
    public JsonArray getTemplate() {
        return template;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
