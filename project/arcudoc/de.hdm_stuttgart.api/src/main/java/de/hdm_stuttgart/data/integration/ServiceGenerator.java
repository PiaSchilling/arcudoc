package de.hdm_stuttgart.data.integration;

import de.hdm_stuttgart.data.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Generate API Clients
 */
public class ServiceGenerator {

    private static final Logger log = LogManager.getLogger();

    public void getClient(Client client){
        switch (client){
            //case SUPABASE -> {return buildRetrofit("https://tkulvivaltoyyguivrsh.supabase.co/").create(SupabaseBaseClient.class);}
            //case GITLAB -> {return buildRetrofit("https://gitlab.com/api/v4/").create(GitLabClient.class);}
            default -> log.error("No such client defined: " + client);
        }
    }

    private Retrofit buildRetrofit(String baseUrl){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        return retrofitBuilder.build();
    }



}
