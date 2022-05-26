package de.hdm_stuttgart.docu.data;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.docu.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;


import java.util.List;


public class DocuRepo {

    private static final Logger log = LogManager.getLogger(DocuRepo.class);
    private final SupabaseRestClient supabaseRestClient = ServiceProvider.getSupabaseRestClient();
    private final int projectId = 1;


    /**
     * calls corresponding api endpoints for getting template text for Chapter (based on the project ID)
     */
    public void getTemplate() {

        Call<List<TemplateResponse>> call = supabaseRestClient.getTemplate(
                ApiConstants.API_KEY,
                ApiConstants.BEARER_KEY,
                "eq." + projectId

        );


    }

}
