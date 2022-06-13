package de.hdm_stuttgart.docu.service;

public interface IDocu {

    void fetchTemplate();

    String getProjectName();

    void onLogoCLicked();


    /* Chapter 1 */
    void onAufgabenstellungClicked();
    void onQualitaetszieleClicked();
    void onStakeholderClicked();

    /* Chapter 2 */
    void onTechnischeRandbedigungenClicked();
    void onOrganisatorischeRandbedigungenClicked();
    void onKonventionenClicked();

    /* Chapter 3 */
    void onFachlicherKontextClicked();
    void onVerteilungskontextClicked();

    /* Chapter 4 */
    void onLoesungsstrategieClicked();

    /* Chapter 5 */
    void onBausteinsichtEbeneEinsClicked();
    void onBausteinsichtEbeneZweiClicked();

    /* Chapter 6 */
    void onLaufzeitszenarioEinsCLicked();
    void onLaufzeitszenarioZweiClicked();

    /* Chapter 7 */
    void onInfrastrukturEbeneEinsClicked();
    void onInfrastrukturEbeneZweiClicked();

    /* Chapter 8 */
    void onFachlicheStrukturClicked();
    void onArchitekturUndEntwurfsmusterClicked();

    /* Chapter 9 */
    void onEntwurfsentscheidungEinsClicked();
    void onEntwurfsentscheidnungZweiClicked();

    /* Chapter 10 */
    void onQualitaetsbaumClicked();
    void onQualitaetsszenarienClicked();

    /* Chapter 11 */
    void onRisikenClicked();

    /* Chapter 12 */
    void onGlossarClicked();
}
