module de.hdm.stuttgart.docu {
    exports de.hdm_stuttgart.docu.service;
    exports de.hdm_stuttgart.docu.guice;

    exports de.hdm_stuttgart.docu.integration to com.google.guice;

    requires com.google.guice;
}