package com.hambrospecial.my_work.integration_token_management;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class TokenService {

    private final TokenManager tokenManager;
    private final RestTemplate restTemplate;
    private final String tokenUrl = "https://example.com/token";

    public TokenService(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
        this.restTemplate = new RestTemplate();
    }

    public String getValidToken() {
        String token = tokenManager.getToken();
        if (token == null) {
            synchronized (this) {
                token = tokenManager.getToken(); // Double-check after acquiring lock
                if (token == null) {
                    token = fetchNewToken();
                }
            }
        }
        return token;
    }

    private String fetchNewToken() {
        // Make API call to fetch the token
//        ResponseEntity<TokenResponse> response = restTemplate.postForEntity(tokenUrl, null, TokenResponse.class);
        System.out.println("==========>> Token api is called for new token =========================>");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(UUID.randomUUID().toString());
        tokenResponse.setExpiresIn(50);
        ResponseEntity<TokenResponse> response = new ResponseEntity<>(tokenResponse, HttpStatusCode.valueOf(200));

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String newToken = response.getBody().getToken();
            long expiresIn = response.getBody().getExpiresIn();
            tokenManager.updateToken(newToken, expiresIn);
            return newToken;
        }

        throw new RuntimeException("Failed to fetch token");
    }
}


