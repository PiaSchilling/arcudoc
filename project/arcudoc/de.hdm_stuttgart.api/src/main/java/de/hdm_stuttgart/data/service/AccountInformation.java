package de.hdm_stuttgart.data.service;

import de.hdm_stuttgart.data.api.ProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountInformation {

    private static AccountInformation INSTANCE;

    private final ProfileRepository profileRepository = new ProfileRepository(); //todo inject
    private static final Logger log = LogManager.getLogger(AccountInformation.class);

    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private long tokenTimestamp;

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
        profileRepository.setRefreshTokenToPreferences(refreshToken);
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
            log.info("Access token expired, fetch new token");
            profileRepository.fetchProfileSync();
        }
        return accessToken;
    }

    /**
     * calculates if the access token is expired
     * @return true if expired false if not
     */
    private boolean isExpired(){
        log.debug("token time stamp " + tokenTimestamp + ", expires in " + expiresIn);
        return tokenTimestamp + expiresIn < System.currentTimeMillis();
    }

}
