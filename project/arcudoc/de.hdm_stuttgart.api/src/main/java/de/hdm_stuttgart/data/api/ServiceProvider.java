package de.hdm_stuttgart.data.api;

import de.hdm_stuttgart.data.service.ApiConstants;
import de.hdm_stuttgart.data.service.GitLabClient;
import de.hdm_stuttgart.data.service.SupabaseAuthClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {



    public static SupabaseAuthClient getSupabaseAuthClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiConstants.SUPABASE_AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(SupabaseAuthClient.class);
    }

    public static GitLabClient getGitLabClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiConstants.GITLAB_MARKDOWN_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(GitLabClient.class);
    }

}
