package de.hdm_stuttgart.login.integration;

import de.hdm_stuttgart.login.model.AuthProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private LoginController loginController;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
    }

    @Test
    public void should_ReturnGitLabUrl_When_ProviderGitLab(){
        assertEquals("https://etbkjwehwsuofhjxpsuq.supabase.co/auth/v1/authorize?provider=gitlab"
        ,loginController.getAuthUrl(AuthProvider.GITLAB));
    }

    @Test
    public void should_ReturnGoogleUrl_When_ProviderGoogle(){
        assertEquals("https://etbkjwehwsuofhjxpsuq.supabase.co/auth/v1/authorize?provider=google"
                ,loginController.getAuthUrl(AuthProvider.GOOGLE));
    }
}