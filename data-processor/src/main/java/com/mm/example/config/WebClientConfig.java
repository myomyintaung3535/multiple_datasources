package com.mm.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE) // Set default headers if needed
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE) // Set default accept header
                .build();
    }

}
