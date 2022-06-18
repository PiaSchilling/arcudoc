package de.hdm_stuttgart.docu.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import de.hdm_stuttgart.docu.service.ITemplateResponse;


public class TemplateResponse implements ITemplateResponse {

    @SerializedName("content")
    private JsonArray template;

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private int projectId;

    private String einfuehrungUndZiele;

    private String aufgabenstellung;

    private String qualiteatsziele;

    private String stakeholder;

    private String randbedingungen;

    private String technischeRandbedingungen;

    private String organisatorischeRandbedingungen;

    private String konventionen;

    private String kontextabgrenzug;

    private String fachlicherKontext;

    private String technischerVerteilungskontext;

    private String lösungsstrategien;

    private String bausteinsicht;

    private String bausteinsichtEbeneEins;

    private String bausteinsichtEbendeZwei;

    private String Laufzeitsicht;

    private String LaufzeitsichtSzenarioEins;

    private String LaufzeitsichtSzenarioZwei;

    private String vertreilungssicht;

    private String infrastrukurEbeneEins;

    private String infrastrukurEbeneZwei;

    private String querschnittlicheKonzepte;

    private String FachStrukturUndModelle;

    private String architekturUndEntwurfsmuster;

    private String entwurfsentscheidung;

    private String entwurfsentscheidungEins;

    private String entwurfsentscheidungZwei;

    private String qualitätsanforderungen;

    private String qualitätsbaum;

    private String qualitätszenarien;

    private String risikenUndTechnischeSchulden;

    private String glosar;











    public TemplateResponse(JsonArray template, String title, Integer projectId){
        this.template = template;
        this.title = title;
        this.projectId = projectId;
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

    @Override
    public void setTitle(String test) {
        this.title = title;
    }


    @Override
    public void setTemplate(JsonArray template) {
        this.template = template;
    }

    @Override
    public Integer getProjectId() {
        return projectId;
    }


}
