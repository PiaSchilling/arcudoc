package de.hdm_stuttgart.docu.data;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.docu.model.*;
import de.hdm_stuttgart.docu.service.ITemplateResponse;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



public class DocuRepo {

    private static final Logger log = LogManager.getLogger(DocuRepo.class);
    private final SupabaseRestClient supabaseRestClient = ServiceProvider.getSupabaseRestClient();
    private int projectId;
    private List template;

    private final ListProperty<TemplateResponse> templateResponseProperty = new SimpleListProperty<>();


    /**
     * calls corresponding api endpoints for getting template text for Chapter (based on the project ID)
     */
    public ITemplateResponse getTemplate(Integer projectId) {

        CompletableFuture<ITemplateResponse> futureTask = new CompletableFuture<>();
        Call<List<TemplateResponse>> call = supabaseRestClient.getTemplate(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "*",
                "eq." + projectId


        );

        this.projectId = projectId;

        call.enqueue(new Callback<List<TemplateResponse>>() {
            @Override
            public void onResponse(Call<List<TemplateResponse>> call, Response<List<TemplateResponse>> response) {
                if (response.isSuccessful()) {
                    log.debug(response.code() + " Template has been loaded");
                    template = response.body();
                    // templateResponseProperty.setValue(FXCollections.observableList(template));
                    futureTask.complete((ITemplateResponse) template.get(0));

                } else {
                    log.error(response.code() + response.message() + " Template was not loaded ");

                }
            }

            @Override
            public void onFailure(Call<List<TemplateResponse>> call, Throwable throwable) {
                log.error(throwable.getMessage() + " Template was not loaded - fetch failed");
            }
        });

        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } return null;
    }

    public void setContent(TemplateResponse templateResponse){

        Call<Void> call = supabaseRestClient.setContent(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "application/json",
                "return=representation",
                "eq." + projectId,
                templateResponse

        );
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    log.debug(response.code() + " - content added successfully");
                } else {
                    log.error(response.code() +  response.message() + " - no content added");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                log.error(throwable.getMessage() + " - failed to add content");
            }
        });


    }

    public ListProperty<TemplateResponse> getTemplateResponseProperty() {
        return templateResponseProperty;
    }

}
