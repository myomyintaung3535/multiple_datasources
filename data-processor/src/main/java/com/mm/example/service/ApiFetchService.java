package com.mm.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ApiFetchService {

    private final WebClient webClient;

    @Value("${spring.application.api-url}]")
    private String apiUrl;

    public String fetchDataFromApi(String apiUrl) {
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocking call to wait for the response
    }
}
