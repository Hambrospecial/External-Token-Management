package com.hambrospecial.my_work.integration_token_management;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    private String token;
    private long expirationTime; // Store the token expiration timestamp in milliseconds

    private static final long TOKEN_EXPIRATION_BUFFER = 30 * 1000; // 30 seconds buffer

    // Synchronized method to get the token
    public synchronized String getToken() {
        if (isTokenExpired()) {
            return null; // Token has expired
        }
        return token;
    }

    public synchronized void updateToken(String newToken, long validForSeconds) {
        this.token = newToken;
        this.expirationTime = System.currentTimeMillis() + (validForSeconds * 1000);
    }

    private boolean isTokenExpired() {
        return System.currentTimeMillis() >= (expirationTime - TOKEN_EXPIRATION_BUFFER);
    }
}

