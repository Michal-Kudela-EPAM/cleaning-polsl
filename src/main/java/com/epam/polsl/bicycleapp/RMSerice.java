package com.epam.polsl.bicycleapp;

import com.epam.polsl.bicycleapp.model.BicycleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RMSerice implements BicycleVendorInterface {

    private final RestTemplate restTemplate;
    Gson gson = new Gson();

    public List<BicycleDTO> findBikes(
            String manufacturer,
            String maximumWeight,
            String minimumWeight,
            String type,
            String frameTypes,
            String electric
    ) {
        List<BicycleDTO> bikes = new ArrayList<>();
        if(manufacturer == null || manufacturer.toUpperCase().contains("RM")){
            StringBuilder rometUrl = new StringBuilder("http://localhost:8080/bicycles/rm/search");
            if(manufacturer != null || type != null || frameTypes != null || electric != null || maximumWeight != null || minimumWeight != null ) {
                rometUrl.append("?");
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
        return bikes;
    }
}
