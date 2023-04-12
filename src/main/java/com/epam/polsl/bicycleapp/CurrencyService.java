package com.epam.polsl.bicycleapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final RestTemplate restTemplate;

    public double convert(double price, String inputCurrency, String outputCurrency) {
        Double targetExchange = restTemplate.getForObject("http://localhost:8080/currency/exchange/" + outputCurrency,
                Double.class);
        Double exchange = restTemplate.getForObject("http://localhost:8080" +
                        "/currency/exchange/" + inputCurrency,
                Double.class);
        double priceInEur = price * exchange;
        double priceInTarget = priceInEur / targetExchange;
        return priceInTarget;
    }
}
