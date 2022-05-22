package de.hdm_stuttgart.docu.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.service.IDocu;

public class Docu implements IDocu {

    private final DocuController controller;

    @Inject
    public Docu(DocuController controller) {
        this.controller = controller;
    }

    @Override
    public String getProjectName() {
        return null;
    }

    @Override
    public void onLogoCLicked() {

    }

    @Override
    public void onAufgabenstellungClicked() {
        controller.printTest();
    }

    @Override
    public void onQualitaetszieleClicked() {

    }

    @Override
    public void onStakeholderClicked() {

    }

    @Override
    public void onTechnischeRandbedigungenClicked() {

    }

    @Override
    public void onOrganisatorischeRandbedigungenClicked() {

    }

    @Override
    public void onKonventionenClicked() {

    }

    @Override
    public void onFachlicherKontextClicked() {

    }

    @Override
    public void onVerteilungskontextClicked() {

    }

    @Override
    public void onLoesungsstrategieClicked() {

    }

    @Override
    public void onBausteinsichtEbeneEinsClicked() {

    }

    @Override
    public void onBausteinsichtEbeneZweiClicked() {

    }

    @Override
    public void onLaufzeitszenarioEinsCLicked() {

    }

    @Override
    public void onLaufzeitszenarioZweiClicked() {

    }

    @Override
    public void onInfrastrukturEbeneEinsClicked() {

    }

    @Override
    public void onInfrastrukturEbeneZweiClicked() {

    }

    @Override
    public void onFachlicheStrukturClicked() {

    }

    @Override
    public void onArchitekturUndEntwurfsmusterClicked() {

    }

    @Override
    public void onEntwurfsentscheidungEinsClicked() {

    }

    @Override
    public void onEntwurfsentscheidnungZweiClicked() {

    }

    @Override
    public void onQualitaetsbaumClicked() {

    }

    @Override
    public void onQualitaetsszenarienClicked() {

    }

    @Override
    public void onRisikenClicked() {

    }

    @Override
    public void onGlossarClicked() {

    }
}
