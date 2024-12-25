package com.hambrospecial.my_work.integration_token_management;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ApiService {

    private final TokenService tokenService;
    private final RestTemplate restTemplate;

    public ApiService(TokenService tokenService) {
        this.tokenService = tokenService;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedDelayString = "600", initialDelayString = "600")
    public void callApi1() {
        String token = tokenService.getValidToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = "https://example.com/api1";
        HttpEntity<Void> request = new HttpEntity<>(headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        System.out.println("API 1 Response: " + response.getBody());
        System.out.println("Response from API - ".concat(url).concat(" : ").concat(String.valueOf(request)));
    }

    @Scheduled(fixedDelayString = "1200", initialDelayString = "1200")
    public void callApi2() {
        String token = tokenService.getValidToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        String url = "https://example.com/api2";
        HttpEntity<Void> request = new HttpEntity<>(headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//        System.out.println("API 2 Response: " + response.getBody());
        System.out.println("Response from API - ".concat(url).concat(" : ").concat(String.valueOf(request)));
    }
}

