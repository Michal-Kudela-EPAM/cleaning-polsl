package com.epam.polsl.bicycleapp;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
