package de.hdm_stuttgart.data.service;

import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.api.SupabaseAuthClient;

public final class ApiConstants {

    private final static SupabaseAuthClient authClient = ServiceProvider.getSupabaseAuthClient(); //todo inject once guice is introduced

    public final static String supabaseAuthUrlBase = "https://tkulvivaltoyyguivrsh.supabase.co/auth/v1/";
    public final static String supabaseAuthUrl = "https://tkulvivaltoyyguivrsh.supabase.co/auth/v1/authorize?provider="; //todo refactor

    public final static String supabaseRestUrl = "https://tkulvivaltoyyguivrsh.supabase.co/rest/v1/";

    public final static String apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRrdWx2aXZhbHRveXlndWl2cnNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NDg0OTk4NjgsImV4cCI6MTk2NDA3NTg2OH0.smUOZMVWkh6GoxYDuxM6Y8pkxySBUYz3ZxJTSihAY5o";

    private static String accessToken; //todo move to profile class (api constants should not be responsible for that)
    private static String refreshToken;
    private static long expiresIn;
    private static long tokenTimestamp;
    private static boolean expired;


    public static String getAccessToken() {
        if(isExpired()){
            authClient.getAccesstokenWithRefreshToken(apiKey,refreshToken);
            return null; //todo implement action when token is expired (fetch new token)
        }else{
            return accessToken;
        }
    }

    private static boolean isExpired() {
        expired = tokenTimestamp + expiresIn < System.currentTimeMillis();
        return expired;
    }

    public static void setAccessToken(String accessToken) {
        ApiConstants.accessToken = accessToken;
    }

    public static void setRefreshToken(String refreshToken) {
        ApiConstants.refreshToken = refreshToken;
    }

    public static void setExpiresIn(long expiresIn) {
        ApiConstants.expiresIn = expiresIn;
    }

    public static void setTokenTimestamp(long tokenTimestamp) {
        ApiConstants.tokenTimestamp = tokenTimestamp;
    }
}
