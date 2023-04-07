package com.epam.polsl.bicycleapp;

import com.epam.polsl.bicycleapp.dataProviders.BicycleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FindBike {

    @GetMapping
    public List<BicycleDTO> findBikes(
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "maximumWeight", required = false) String maximumWeight,
            @RequestParam(value = "minimumWeight", required = false) String minimumWeight,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "frameTypes", required = false) String frameTypes,
            @RequestParam(value = "electric", required = false) String electric,
            @RequestParam(value = "maxPrice", required = false) String maxPrice,
            @RequestParam(value = "minPrice", required = false) String minPrice,
            @RequestParam(value = "currency", required = false) String currency
    ) {
        RestTemplate restTemplate = new RestTemplate();
        List<BicycleDTO> bikes = new ArrayList<>();
        Gson gson = new Gson();

        if((maxPrice != null || minPrice != null) && currency == null) {
            throw new RuntimeException("cannot have min/max price without currency");
        }

        // get Romet bikes
        if(manufacturer == null || manufacturer.toUpperCase().contains("ROMET")){
            StringBuilder rometUrl = new StringBuilder("http://localhost:8080/rowery-pl/romet");
            if(manufacturer != null || type != null || frameTypes != null || electric != null || minimumWeight != null ) {
                rometUrl.append("?");
//            }
                if(electric != null) {
                    rometUrl.append("electric=").append(electric);
                }
                if(type != null) {
                    rometUrl.append("type=").append(type);
                }
                if(maximumWeight != null) {
                    rometUrl.append("maximumWeight=").append(maximumWeight);
                }
                if(minimumWeight != null) {
                    rometUrl.append("minimumWeight=").append(minimumWeight);
                }
                if(frameTypes != null) {
                    rometUrl.append("frameTypes=").append(frameTypes);
                }
            }

            for(int i = 0; i < 3; i++) {
                try{
                    String json = restTemplate.getForObject(rometUrl.toString(), String.class);
                    Type bicycleDtoType = new TypeToken<List<BicycleDTO>>(){}.getType();
                    if(json != null)
                        bikes.addAll(gson.fromJson(json, bicycleDtoType));
                    break;
                } catch (RuntimeException ignored) {

                }
            }


        }

        // Get RM bikes
        if(manufacturer == null || manufacturer.toUpperCase().contains("RM")){
            StringBuilder rometUrl = new StringBuilder("http://localhost:8080/bicycles/rm/search");
            if(manufacturer != null || type != null || frameTypes != null || electric != null || maximumWeight != null || minimumWeight != null ) {
                rometUrl.append("?");
//            }
                if(electric != null) {
                    rometUrl.append("electric=").append(electric);
                }
                if(type != null) {
                    rometUrl.append("type=").append(type);
                }
                if(maximumWeight != null) {
                    rometUrl.append("maximumWeight=").append(maximumWeight);
                }
                if(maximumWeight != null) {
                    rometUrl.append("minimumWeight=").append(minimumWeight);
                }
                if(frameTypes != null) {
                    rometUrl.append("frameTypes=").append(frameTypes);
                }
            }

            for (int i = 0; i < 5; i++) {
                try{
                    String json = restTemplate.getForObject(rometUrl.toString(), String.class);
                    Type bicycleDtoType = new TypeToken<List<BicycleDTO>>(){}.getType();
                    if(json != null)
                        bikes.addAll(gson.fromJson(json, bicycleDtoType));
                    break;
                } catch (RuntimeException ignored) {

                }
            }


        }

        //Convert currencies
        if(currency != null) {
            Double targetExchange = restTemplate.getForObject("http://localhost:8080/currency/exchange/" + currency,
                    Double.class);


            for(BicycleDTO bicycleDTO : bikes) {
                Double bikeExchange = restTemplate.getForObject("http://localhost:8080" +
                                "/currency/exchange/" + bicycleDTO.getCurrency(),
                        Double.class);
                double priceInEur = bicycleDTO.getPrice() * bikeExchange;
                double priceInTarget = priceInEur / targetExchange;
                bicycleDTO.setPrice(priceInTarget);
            }

            //Filter by price
            if(maxPrice != null) {
                double maxPriceDbl = Double.parseDouble(maxPrice);
                ArrayList<BicycleDTO> matchingBikes = new ArrayList<>();
                for(BicycleDTO bicycleDTO : bikes) {
                    if(bicycleDTO.getPrice() <= maxPriceDbl)
                        matchingBikes.add(bicycleDTO);
                }
                bikes = matchingBikes;
            }

            if(minPrice != null) {
                double minPriceDbl = Double.parseDouble(minPrice);
                ArrayList<BicycleDTO> matchingBikes = new ArrayList<>();
                for(BicycleDTO bicycleDTO : bikes) {
                    if(bicycleDTO.getPrice() >= minPriceDbl)
                        matchingBikes.add(bicycleDTO);
                }
                bikes = matchingBikes;
            }
        }


        return bikes;
    }
}
