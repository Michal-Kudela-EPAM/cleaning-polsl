package com.epam.polsl.bicycleapp;

import com.epam.polsl.bicycleapp.model.BicycleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class FindBike {

    private final FindBikeService findBikeService;

    @GetMapping("bicycle-finder")
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
        return findBikeService.findBikes(manufacturer, maximumWeight, minimumWeight, type, frameTypes, electric,
                maxPrice, minPrice, currency);
    }
}
