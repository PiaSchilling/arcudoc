module workspace {
    exports de.hdm_stuttgart.workspace.service;
    exports de.hdm_stuttgart.workspace.guice;
    exports de.hdm_stuttgart.workspace.integration;

    requires com.google.guice;
    requires org.apache.logging.log4j;

}