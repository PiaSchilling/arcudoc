package de.hdm_stuttgart.docu.model;
import com.google.gson.annotations.SerializedName;


public class TemplateResponse implements de.hdm_stuttgart.docu.service.ITemplateResponse {


    @SerializedName("id")
    private int projectId;

    @SerializedName("1.0_Einführung-und-Ziele")
    private String einfuehrungUndZiele;

    @SerializedName("1.1_Aufgabenstellung")
    private String aufgabenstellung;

    @SerializedName("1.2_Qualiteatsziele")
    private String qualiteatsziele;

    @SerializedName("1.3_Stakeholder")
    private String stakeholder;

    @SerializedName("2.0_Randbedingungen")
    private String randbedingungen;

    @SerializedName("2.1_Technische-Randbedingungen")
    private String technischeRandbedingungen;

    @SerializedName("2.2_Organisatorische-Randbedingungen")
    private String organisatorischeRandbedingungen;

    @SerializedName("2.3_Konventionen")
    private String konventionen;

    @SerializedName("3.0_Kontextabgrenzug")
    private String kontextabgrenzug;

    @SerializedName("3.1_Fachlicher-Kontex")
    private String fachlicherKontext;

    @SerializedName("3.2_Technischer-Verteilungskontexz")
    private String technischerVerteilungskontext;

    @SerializedName("4.0_Lösungsstrategien")
    private String lösungsstrategien;

    @SerializedName("5.0_Bausteinsicht")
    private String bausteinsicht;

    @SerializedName("5.1_Ebene-1")
    private String bausteinsichtEbeneEins;

    @SerializedName("5.2_Ebene-2")
    private String bausteinsichtEbendeZwei;

    @SerializedName("6.0_Laufzeitsicht")
    private String Laufzeitsicht;

    @SerializedName("6.1_Laufzeitszenario-1")
    private String LaufzeitsichtSzenarioEins;

    @SerializedName("6.2_Laufzeitszenario-2")
    private String LaufzeitsichtSzenarioZwei;

    @SerializedName("7.0_Verteilungssicht")
    private String vertreilungssicht;

    @SerializedName("7.1_Infrastrukur-Ebene-1")
    private String infrastrukurEbeneEins;

    @SerializedName("7.2_Infrastrukur-Ebene-2")
    private String infrastrukurEbeneZwei;

    @SerializedName("8.0_Querschnittliche-Konzepte")
    private String querschnittlicheKonzepte;

    @SerializedName("8.1_Struktur-Modelle")
    private String FachStrukturUndModelle;

    @SerializedName("8.2_Architektur")
    private String architekturUndEntwurfsmuster;

    @SerializedName("9.0_Entwurfsentscheidung")
    private String entwurfsentscheidung;

    @SerializedName("9.1_Entwurfsentscheidung-1")
    private String entwurfsentscheidungEins;

    @SerializedName("9.2_Entwurfsentscheidung-2")
    private String entwurfsentscheidungZwei;

    @SerializedName("10.0_Qualitätsanforderungen")
    private String qualitätsanforderungen;

    @SerializedName("10.1_Qualitätsbaum")
    private String qualitätsbaum;

    @SerializedName("10.2_Qualitätsszenarien")
    private String qualitätszenarien;

    @SerializedName("11.0_Risiken-und-technische-Schulden")
    private String risikenUndTechnischeSchulden;

    @SerializedName("12.0_Glossar")
    private String glosar;

    public TemplateResponse(int projectId, String einfuehrungUndZiele, String aufgabenstellung, String qualiteatsziele, String stakeholder, String randbedingungen, String technischeRandbedingungen, String organisatorischeRandbedingungen, String konventionen, String kontextabgrenzug, String fachlicherKontext, String technischerVerteilungskontext, String lösungsstrategien, String bausteinsicht, String bausteinsichtEbeneEins, String bausteinsichtEbendeZwei, String laufzeitsicht, String laufzeitsichtSzenarioEins, String laufzeitsichtSzenarioZwei, String vertreilungssicht, String infrastrukurEbeneEins, String infrastrukurEbeneZwei, String querschnittlicheKonzepte, String fachStrukturUndModelle, String architekturUndEntwurfsmuster, String entwurfsentscheidung, String entwurfsentscheidungEins, String entwurfsentscheidungZwei, String qualitätsanforderungen, String qualitätsbaum, String qualitätszenarien, String risikenUndTechnischeSchulden, String glosar) {
        this.projectId = projectId;
        this.einfuehrungUndZiele = einfuehrungUndZiele;
        this.aufgabenstellung = aufgabenstellung;
        this.qualiteatsziele = qualiteatsziele;
        this.stakeholder = stakeholder;
        this.randbedingungen = randbedingungen;
        this.technischeRandbedingungen = technischeRandbedingungen;
        this.organisatorischeRandbedingungen = organisatorischeRandbedingungen;
        this.konventionen = konventionen;
        this.kontextabgrenzug = kontextabgrenzug;
        this.fachlicherKontext = fachlicherKontext;
        this.technischerVerteilungskontext = technischerVerteilungskontext;
        this.lösungsstrategien = lösungsstrategien;
        this.bausteinsicht = bausteinsicht;
        this.bausteinsichtEbeneEins = bausteinsichtEbeneEins;
        this.bausteinsichtEbendeZwei = bausteinsichtEbendeZwei;
        Laufzeitsicht = laufzeitsicht;
        LaufzeitsichtSzenarioEins = laufzeitsichtSzenarioEins;
        LaufzeitsichtSzenarioZwei = laufzeitsichtSzenarioZwei;
        this.vertreilungssicht = vertreilungssicht;
        this.infrastrukurEbeneEins = infrastrukurEbeneEins;
        this.infrastrukurEbeneZwei = infrastrukurEbeneZwei;
        this.querschnittlicheKonzepte = querschnittlicheKonzepte;
        FachStrukturUndModelle = fachStrukturUndModelle;
        this.architekturUndEntwurfsmuster = architekturUndEntwurfsmuster;
        this.entwurfsentscheidung = entwurfsentscheidung;
        this.entwurfsentscheidungEins = entwurfsentscheidungEins;
        this.entwurfsentscheidungZwei = entwurfsentscheidungZwei;
        this.qualitätsanforderungen = qualitätsanforderungen;
        this.qualitätsbaum = qualitätsbaum;
        this.qualitätszenarien = qualitätszenarien;
        this.risikenUndTechnischeSchulden = risikenUndTechnischeSchulden;
        this.glosar = glosar;
    }

    public TemplateResponse() {

    }

    // ----- Getter -----

    @Override
    public String getEinfuehrungUndZiele() {
        return einfuehrungUndZiele;
    }

    @Override
    public String getAufgabenstellung() {
        return aufgabenstellung;
    }

    @Override
    public String getQualiteatsziele() {
        return qualiteatsziele;
    }

    @Override
    public String getStakeholder() {
        return stakeholder;
    }

    @Override
    public String getRandbedingungen() {
        return randbedingungen;
    }

    @Override
    public String getTechnischeRandbedingungen() {
        return technischeRandbedingungen;
    }

    @Override
    public String getOrganisatorischeRandbedingungen() {
        return organisatorischeRandbedingungen;
    }

    @Override
    public String getKonventionen() {
        return konventionen;
    }

    @Override
    public String getKontextabgrenzug() {
        return kontextabgrenzug;
    }

    @Override
    public String getFachlicherKontext() {
        return fachlicherKontext;
    }

    @Override
    public String getTechnischerVerteilungskontext() {
        return technischerVerteilungskontext;
    }

    @Override
    public String getLösungsstrategien() {
        return lösungsstrategien;
    }

    @Override
    public String getBausteinsicht() {
        return bausteinsicht;
    }

    @Override
    public String getBausteinsichtEbeneEins() {
        return bausteinsichtEbeneEins;
    }

    @Override
    public String getBausteinsichtEbendeZwei() {
        return bausteinsichtEbendeZwei;
    }

    @Override
    public String getLaufzeitsicht() {
        return Laufzeitsicht;
    }

    @Override
    public String getLaufzeitsichtSzenarioEins() {
        return LaufzeitsichtSzenarioEins;
    }

    @Override
    public String getLaufzeitsichtSzenarioZwei() {
        return LaufzeitsichtSzenarioZwei;
    }

    @Override
    public String getVertreilungssicht() {
        return vertreilungssicht;
    }

    @Override
    public String getInfrastrukurEbeneEins() {
        return infrastrukurEbeneEins;
    }

    @Override
    public String getInfrastrukurEbeneZwei() {
        return infrastrukurEbeneZwei;
    }

    @Override
    public String getQuerschnittlicheKonzepte() {
        return querschnittlicheKonzepte;
    }

    @Override
    public String getFachStrukturUndModelle() {
        return FachStrukturUndModelle;
    }

    @Override
    public String getArchitekturUndEntwurfsmuster() {
        return architekturUndEntwurfsmuster;
    }

    @Override
    public String getEntwurfsentscheidung() {
        return entwurfsentscheidung;
    }

    @Override
    public String getEntwurfsentscheidungEins() {
        return entwurfsentscheidungEins;
    }

    @Override
    public String getEntwurfsentscheidungZwei() {
        return entwurfsentscheidungZwei;
    }

    @Override
    public String getQualitätsanforderungen() {
        return qualitätsanforderungen;
    }

    @Override
    public String getQualitätsbaum() {
        return qualitätsbaum;
    }

    @Override
    public String getQualitätszenarien() {
        return qualitätszenarien;
    }

    @Override
    public String getRisikenUndTechnischeSchulden() {
        return risikenUndTechnischeSchulden;
    }

    @Override
    public String getGlosar() {
        return glosar;
    }

    @Override
    public int getProjectId() {
        return projectId;
    }

    // ---- Setter -----


    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public void setEinfuehrungUndZiele(String einfuehrungUndZiele) {
        this.einfuehrungUndZiele = einfuehrungUndZiele;
    }

    @Override
    public void setAufgabenstellung(String aufgabenstellung) {
        this.aufgabenstellung = aufgabenstellung;
    }

    @Override
    public void setQualiteatsziele(String qualiteatsziele) {
        this.qualiteatsziele = qualiteatsziele;
    }

    @Override
    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    @Override
    public void setRandbedingungen(String randbedingungen) {
        this.randbedingungen = randbedingungen;
    }

    @Override
    public void setTechnischeRandbedingungen(String technischeRandbedingungen) {
        this.technischeRandbedingungen = technischeRandbedingungen;
    }

    @Override
    public void setOrganisatorischeRandbedingungen(String organisatorischeRandbedingungen) {
        this.organisatorischeRandbedingungen = organisatorischeRandbedingungen;
    }

    @Override
    public void setKonventionen(String konventionen) {
        this.konventionen = konventionen;
    }

    @Override
    public void setKontextabgrenzug(String kontextabgrenzug) {
        this.kontextabgrenzug = kontextabgrenzug;
    }

    @Override
    public void setFachlicherKontext(String fachlicherKontext) {
        this.fachlicherKontext = fachlicherKontext;
    }

    @Override
    public void setTechnischerVerteilungskontext(String technischerVerteilungskontext) {
        this.technischerVerteilungskontext = technischerVerteilungskontext;
    }

    @Override
    public void setLösungsstrategien(String lösungsstrategien) {
        this.lösungsstrategien = lösungsstrategien;
    }

    @Override
    public void setBausteinsicht(String bausteinsicht) {
        this.bausteinsicht = bausteinsicht;
    }

    @Override
    public void setBausteinsichtEbeneEins(String bausteinsichtEbeneEins) {
        this.bausteinsichtEbeneEins = bausteinsichtEbeneEins;
    }

    @Override
    public void setBausteinsichtEbendeZwei(String bausteinsichtEbendeZwei) {
        this.bausteinsichtEbendeZwei = bausteinsichtEbendeZwei;
    }

    @Override
    public void setLaufzeitsicht(String laufzeitsicht) {
        Laufzeitsicht = laufzeitsicht;
    }

    @Override
    public void setLaufzeitsichtSzenarioEins(String laufzeitsichtSzenarioEins) {
        LaufzeitsichtSzenarioEins = laufzeitsichtSzenarioEins;
    }

    @Override
    public void setLaufzeitsichtSzenarioZwei(String laufzeitsichtSzenarioZwei) {
        LaufzeitsichtSzenarioZwei = laufzeitsichtSzenarioZwei;
    }

    @Override
    public void setVertreilungssicht(String vertreilungssicht) {
        this.vertreilungssicht = vertreilungssicht;
    }

    @Override
    public void setInfrastrukurEbeneEins(String infrastrukurEbeneEins) {
        this.infrastrukurEbeneEins = infrastrukurEbeneEins;
    }

    @Override
    public void setInfrastrukurEbeneZwei(String infrastrukurEbeneZwei) {
        this.infrastrukurEbeneZwei = infrastrukurEbeneZwei;
    }

    @Override
    public void setQuerschnittlicheKonzepte(String querschnittlicheKonzepte) {
        this.querschnittlicheKonzepte = querschnittlicheKonzepte;
    }

    @Override
    public void setFachStrukturUndModelle(String fachStrukturUndModelle) {
        FachStrukturUndModelle = fachStrukturUndModelle;
    }

    @Override
    public void setArchitekturUndEntwurfsmuster(String architekturUndEntwurfsmuster) {
        this.architekturUndEntwurfsmuster = architekturUndEntwurfsmuster;
    }

    @Override
    public void setEntwurfsentscheidung(String entwurfsentscheidung) {
        this.entwurfsentscheidung = entwurfsentscheidung;
    }

    @Override
    public void setEntwurfsentscheidungEins(String entwurfsentscheidungEins) {
        this.entwurfsentscheidungEins = entwurfsentscheidungEins;
    }

    @Override
    public void setEntwurfsentscheidungZwei(String entwurfsentscheidungZwei) {
        this.entwurfsentscheidungZwei = entwurfsentscheidungZwei;
    }

    @Override
    public void setQualitätsanforderungen(String qualitätsanforderungen) {
        this.qualitätsanforderungen = qualitätsanforderungen;
    }

    @Override
    public void setQualitätsbaum(String qualitätsbaum) {
        this.qualitätsbaum = qualitätsbaum;
    }

    @Override
    public void setQualitätszenarien(String qualitätszenarien) {
        this.qualitätszenarien = qualitätszenarien;
    }

    @Override
    public void setRisikenUndTechnischeSchulden(String risikenUndTechnischeSchulden) {
        this.risikenUndTechnischeSchulden = risikenUndTechnischeSchulden;
    }

    @Override
    public void setGlosar(String glosar) {
        this.glosar = glosar;
    }
}
