package com.example.reactivesystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Configuration for WebClient can be added here
    // For example, you can define a WebClient bean with custom settings
     @Bean
     public WebClient.Builder webClientBuilder() {
         return WebClient.builder();
     }


}
