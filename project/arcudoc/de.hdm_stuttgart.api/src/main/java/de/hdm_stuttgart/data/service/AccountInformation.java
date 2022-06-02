package de.hdm_stuttgart.data.service;

import de.hdm_stuttgart.data.integration.ProfileRepository;

public class AccountInformation {

    private static AccountInformation INSTANCE;

    private final ProfileRepository profileRepository = new ProfileRepository(); //todo inject

    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private long tokenTimestamp;
    private String userMail;

    private AccountInformation(){
        //Singleton access
    }

    /**
     * Singleton accesses for account information
     * @return the only account object that exists
     */
    public static AccountInformation getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AccountInformation();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        this.tokenTimestamp = System.currentTimeMillis();
    }

    /**
     * get the access token, if expired a new one is fetched
     * @return a valid access token
     */
    public String getAccessToken() {
        if(isExpired()){
            profileRepository.fetchProfile(refreshToken);
        }
        return accessToken;
    }

    /**
     * calculates if the access token is expired
     * @return true if expired false if not
     */
    private boolean isExpired(){
        return tokenTimestamp + expiresIn < System.currentTimeMillis();
    }

    public String getUserMail() {
        return userMail;
    }
}
