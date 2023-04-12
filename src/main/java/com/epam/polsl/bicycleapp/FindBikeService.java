package com.epam.polsl.bicycleapp;

import com.epam.polsl.bicycleapp.model.BicycleDTO;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FindBikeService {

    private final List<BicycleVendorInterface> bicycleVendors;
    private final CurrencyService currencyService;

    public List<BicycleDTO> findBikes(
            String manufacturer,
            String maximumWeight,
            String minimumWeight,
            String type,
            String frameTypes,
            String electric,
            String maxPrice,
            String minPrice,
            String currency
    ) {
        List<BicycleDTO> bikes = new ArrayList<>();

        if((maxPrice != null || minPrice != null) && currency == null) {
            throw new RuntimeException("cannot have min/max price without currency");
        }

        for(BicycleVendorInterface vendor : bicycleVendors) {
            bikes.addAll(vendor.findBikes(manufacturer,
                maximumWeight,
                minimumWeight,
                type,
                frameTypes,
                electric));
        }

        //Convert currencies
        if(currency != null) {
            for(BicycleDTO bike : bikes) {
                double price = currencyService.convert(bike.getPrice(), bike.getCurrency(), currency);
                bike.setPrice(price);
                bike.setCurrency(currency.toUpperCase());
            }

            bikes = filterByPrice(maxPrice, minPrice, bikes);
        }

        return bikes;
    }

    private List<BicycleDTO> filterByPrice(String maxPrice, String minPrice, List<BicycleDTO> bikes) {
        Stream<BicycleDTO> bikeStream = bikes.stream();
        if(maxPrice != null){
            double price = Double.parseDouble(maxPrice);
            bikeStream.filter(bike -> bike.getPrice() <= price);
        }
        if(minPrice != null){
            double price = Double.parseDouble(minPrice);
            bikeStream.filter(bike -> bike.getPrice() >= price);
        }

        return bikeStream.toList();
    }
}
