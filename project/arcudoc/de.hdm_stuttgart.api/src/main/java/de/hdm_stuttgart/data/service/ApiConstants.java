package de.hdm_stuttgart.data.service;

import de.hdm_stuttgart.data.api.ServiceProvider;
import de.hdm_stuttgart.data.api.SupabaseAuthClient;

public final class ApiConstants {

    ///private final static SupabaseAuthClient authClient = ServiceProvider.getSupabaseAuthClient(); //todo inject once guice is introduced

    public final static String supabaseAuthUrlBase = "https://tkulvivaltoyyguivrsh.supabase.co/auth/v1/";
    public final static String supabaseAuthUrl = "https://tkulvivaltoyyguivrsh.supabase.co/auth/v1/authorize?provider="; //todo refactor

    public final static String supabaseRestUrl = "https://tkulvivaltoyyguivrsh.supabase.co/rest/v1/";

    public final static String apiKey = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNjUwMTE5MTU0LCJzdWIiOiIyMmViZTliYi1kNWEwLTRmZTMtOTE4Mi0xMjk4NjdhMGRlMjQiLCJlbWFpbCI6InBpYW1hcmllLnNjaGlsbGluZ0BnbWFpbC5jb20iLCJwaG9uZSI6IiIsImFwcF9tZXRhZGF0YSI6eyJwcm92aWRlciI6ImdpdGxhYiIsInByb3ZpZGVycyI6WyJnaXRsYWIiXX0sInVzZXJfbWV0YWRhdGEiOnsiYXZhdGFyX3VybCI6Imh0dHBzOi8vc2VjdXJlLmdyYXZhdGFyLmNvbS9hdmF0YXIvZTg0NmI3MWIxNTg2YjYzZTY3OWM4NmUzMjk0MzA0ZGY_cz04MFx1MDAyNmQ9aWRlbnRpY29uIiwiZW1haWwiOiJwaWFtYXJpZS5zY2hpbGxpbmdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZ1bGxfbmFtZSI6IlBpYSBTY2hpbGxpbmciLCJpc3MiOiJodHRwczovL2dpdGxhYi5jb20iLCJuYW1lIjoiUGlhIFNjaGlsbGluZyIsInBpY3R1cmUiOiJodHRwczovL3NlY3VyZS5ncmF2YXRhci5jb20vYXZhdGFyL2U4NDZiNzFiMTU4NmI2M2U2NzljODZlMzI5NDMwNGRmP3M9ODBcdTAwMjZkPWlkZW50aWNvbiIsInByb3ZpZGVyX2lkIjoiMTA3MjU4OTAiLCJzdWIiOiIxMDcyNTg5MCJ9LCJyb2xlIjoiYXV0aGVudGljYXRlZCJ9.5IXpKw7JIcN36EJuS-_8iVMHVUUQV4AaJbt7BsS2HiY";

    private static String accessToken; //todo move to profile class (api constants should not be responsible for that)
    private static String refreshToken;
    private static long expiresIn;
    private static long tokenTimestamp;
    private static boolean expired;


    public static String getAccessToken() {
        if(isExpired()){
            //authClient.getAccesstokenWithRefreshToken(apiKey,refreshToken);
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
