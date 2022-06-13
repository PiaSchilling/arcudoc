package de.hdm_stuttgart.docu.integration;

import com.google.inject.Inject;
import de.hdm_stuttgart.docu.data.DocuRepo;
import de.hdm_stuttgart.docu.model.TemplateResponse;
import de.hdm_stuttgart.docu.service.ITemplateResponse;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DocuController {

    private final DocuRepo docuRepo;
    private final ListProperty<ITemplateResponse> templateResponseListProperty;

    @Inject
    public DocuController(DocuRepo docuRepo) {
        this.docuRepo = docuRepo;

        ListProperty<TemplateResponse> templateResponseRepo = docuRepo.getTemplateResponseProperty();
        templateResponseListProperty = new SimpleListProperty<>();
        templateResponseRepo.addListener((observable, oldValue, newValue) -> updateTemplateResponseProperty());

    }

    private void updateTemplateResponseProperty(){
        ObservableList<ITemplateResponse> observableList = FXCollections.observableArrayList();
        observableList.addAll(docuRepo.getTemplateResponseProperty());
        templateResponseListProperty.setValue(observableList);
    }

    public ListProperty<ITemplateResponse> getTemplateResponseListProperty() {
        docuRepo.getTemplate();
        return templateResponseListProperty;
    }
}
