package de.hdm_stuttgart.data.api;

import de.hdm_stuttgart.data.service.ApiConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    public static SupabaseAuthClient getSupabaseAuthClient(){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(ApiConstants.supabaseAuthUrlBase)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(SupabaseAuthClient.class);
    }

}
