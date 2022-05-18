package de.hdm_stuttgart.login.integration;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortListenerTest {

    private final PortListener portListener = new PortListener();

    //  - - - - - checkTokens()-Test - - - - - - -
    @Test
    public void should_ThrowException_When_TokensMalformed(){
        String missingAccessTokenKeyWord = "asdlfianndidiene";
        String missingExpiresInKeyWord = "asdlfianndidiene";
        String missingProviderKeyWord = "asdlfianndidiene";
        String missingRefreshKeyWord = "asdlfianndidiene";
        String missingTypeInKeyWord = "asdlfianndidiene";

        String[] malformedTokensArray = {
                missingAccessTokenKeyWord,
                missingExpiresInKeyWord,
                missingProviderKeyWord,
                missingRefreshKeyWord,
                missingTypeInKeyWord
        };

        assertThrows(MalformedTokensException.class, () -> portListener.checkTokens(malformedTokensArray));
    }

    @Test
    public void should_ThrowException_When_TokensMissing(){
        String[] missingTokenArray = new String[4];
        assertThrows(MalformedTokensException.class, () -> portListener.checkTokens(missingTokenArray));
    }

    @Test
    public void should_ReturnTrue_When_TokensValid(){
        String accessToken = "access_token=asdlfianndidiene";
        String expiresIn = "expires_in=3600";
        String providerToken = "provider_token=asddjdjdjd";
        String refreshToken = "refresh_token=asdlfianndidiene";
        String tokenType = "token_type=Bearer";

        String[] validTokensArray = {
                accessToken,
                expiresIn,
                providerToken,
                refreshToken,
                tokenType
        };

        try{
            assertTrue(portListener.checkTokens(validTokensArray));
        }catch (MalformedTokensException e){
            e.printStackTrace();
        }
    }

}