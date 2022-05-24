package de.hdm_stuttgart.docu.data;

import de.hdm_stuttgart.data.api.SupabaseAuthClient;
import de.hdm_stuttgart.data.service.ApiConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    public static SupabaseRestClient getSupabaseRestClient(){ //todo maybe move to data module
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiConstants.SUPABASE_REST_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(SupabaseRestClient.class);
    }


}
