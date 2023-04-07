package com.epam.polsl.bicycleapp.dataProviders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CurrencyController {
    private static final Map<String, Double> valueInEuro = Map.of(
            "EUR", 1.0,
            "PLN", 0.21,
            "USD", 0.92,
            "JEN", 0.007,
            "UAH", 0.025
    );

    @GetMapping("currency/exchange/{currency}")
    public Double exchange(@PathVariable("currency") String currency) {
        return valueInEuro.getOrDefault(currency, 1.0);
    }
}
