package com.epam.polsl.bicycleapp;

import com.epam.polsl.bicycleapp.model.BicycleDTO;

import java.util.List;

public interface BicycleVendorInterface {
    public List<BicycleDTO> findBikes(
            String manufacturer,
            String maximumWeight,
            String minimumWeight,
            String type,
            String frameTypes,
            String electric
    );
}
