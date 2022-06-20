package de.hdm_stuttgart.docu.service;

public interface ITemplateResponse {


    String getEinfuehrungUndZiele();

    String getAufgabenstellung();

    String getQualiteatsziele();

    String getStakeholder();

    String getRandbedingungen();

    String getTechnischeRandbedingungen();

    String getOrganisatorischeRandbedingungen();

    String getKonventionen();

    String getKontextabgrenzug();

    String getFachlicherKontext();

    String getTechnischerVerteilungskontext();

    String getLoesungsstrategien();

    String getBausteinsicht();

    String getBausteinsichtEbeneEins();

    String getBausteinsichtEbendeZwei();

    String getLaufzeitsicht();

    String getLaufzeitsichtSzenarioEins();

    String getLaufzeitsichtSzenarioZwei();

    String getVertreilungssicht();

    String getInfrastrukurEbeneEins();

    String getInfrastrukurEbeneZwei();

    String getQuerschnittlicheKonzepte();

    String getFachStrukturUndModelle();

    String getArchitekturUndEntwurfsmuster();

    String getEntwurfsentscheidung();

    String getEntwurfsentscheidungEins();

    String getEntwurfsentscheidungZwei();

    String getQualitätsanforderungen();

    String getQualitätsbaum();

    String getQualitätszenarien();

    String getRisikenUndTechnischeSchulden();

    String getGlosar();

    int getProjectId();

    void setProjectId(int projectId);

    void setEinfuehrungUndZiele(String einfuehrungUndZiele);

    void setAufgabenstellung(String aufgabenstellung);

    void setQualiteatsziele(String qualiteatsziele);

    void setStakeholder(String stakeholder);

    void setRandbedingungen(String randbedingungen);

    void setTechnischeRandbedingungen(String technischeRandbedingungen);

    void setOrganisatorischeRandbedingungen(String organisatorischeRandbedingungen);

    void setKonventionen(String konventionen);

    void setKontextabgrenzug(String kontextabgrenzug);

    void setFachlicherKontext(String fachlicherKontext);

    void setTechnischerVerteilungskontext(String technischerVerteilungskontext);

    void setLoesungsstrategien(String loesungsstrategien);

    void setBausteinsicht(String bausteinsicht);

    void setBausteinsichtEbeneEins(String bausteinsichtEbeneEins);

    void setBausteinsichtEbendeZwei(String bausteinsichtEbendeZwei);

    void setLaufzeitsicht(String laufzeitsicht);

    void setLaufzeitsichtSzenarioEins(String laufzeitsichtSzenarioEins);

    void setLaufzeitsichtSzenarioZwei(String laufzeitsichtSzenarioZwei);

    void setVertreilungssicht(String vertreilungssicht);

    void setInfrastrukurEbeneEins(String infrastrukurEbeneEins);

    void setInfrastrukurEbeneZwei(String infrastrukurEbeneZwei);

    void setQuerschnittlicheKonzepte(String querschnittlicheKonzepte);

    void setFachStrukturUndModelle(String fachStrukturUndModelle);

    void setArchitekturUndEntwurfsmuster(String architekturUndEntwurfsmuster);

    void setEntwurfsentscheidung(String entwurfsentscheidung);

    void setEntwurfsentscheidungEins(String entwurfsentscheidungEins);

    void setEntwurfsentscheidungZwei(String entwurfsentscheidungZwei);

    void setQualitätsanforderungen(String qualitätsanforderungen);

    void setQualitätsbaum(String qualitätsbaum);

    void setQualitätszenarien(String qualitätszenarien);

    void setRisikenUndTechnischeSchulden(String risikenUndTechnischeSchulden);

    void setGlosar(String glosar);
}
