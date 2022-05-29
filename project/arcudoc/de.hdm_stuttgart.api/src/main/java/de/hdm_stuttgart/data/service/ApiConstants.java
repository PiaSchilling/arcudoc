package de.hdm_stuttgart.data.service;

public final class ApiConstants {

    public final static String SUPABASE_AUTH_URL = "https://etbkjwehwsuofhjxpsuq.supabase.co/auth/v1/";
    public final static String SUPABASE_AUTH_URL_PROVIDER = "https://etbkjwehwsuofhjxpsuq.supabase.co/auth/v1/authorize?provider=";
    public final static String SUPABASE_REST_URL = "https://etbkjwehwsuofhjxpsuq.supabase.co/rest/v1/";

    public final static String BEARER_KEY = "Bearer " + AccountInformation.getInstance().getAccessToken();
    public final static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV0Ymtqd2Vod3N1b2Zoanhwc3VxIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NTA2Mzg3NTUsImV4cCI6MTk2NjIxNDc1NX0.CwMid6i6knuzPDXcmdH7W1YOXapI8WtYMsmYpz61JkM";

    public final static String GITLAB_MARKDOWN_URL = "https://gitlab.com/api/v4/";
}
