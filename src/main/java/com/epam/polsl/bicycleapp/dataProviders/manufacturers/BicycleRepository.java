package com.epam.polsl.bicycleapp.dataProviders.manufacturers;

import com.epam.polsl.bicycleapp.dataProviders.BicycleDTO;
import com.epam.polsl.bicycleapp.dataProviders.BikeSearchDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BicycleRepository {
    public List<BicycleDTO> getBikes(BikeSearchDTO bikeSearchDTO) {
        Stream<BicycleDTO> bikes = allBikes().stream();
        if(bikeSearchDTO.getManufacturer() != null)
            bikes = bikes.filter(bike -> bikeSearchDTO.getManufacturer().equals(bike.getManufacturer()));
        if(bikeSearchDTO.getMaximumWeight() != null)
            bikes = bikes.filter(bike -> bikeSearchDTO.getMaximumWeight() >= bike.getWeight());
        if(bikeSearchDTO.getMinimumWeight() != null)
            bikes = bikes.filter(bike -> bikeSearchDTO.getMinimumWeight() <= bike.getWeight());
        if(bikeSearchDTO.getAllowedTypes() != null)
            bikes = bikes.filter(bike -> bikeSearchDTO.getAllowedTypes().contains(bike.getBicycleType()));
        if(bikeSearchDTO.getFrameTypes() != null)
            bikes = bikes.filter(bike -> bikeSearchDTO.getFrameTypes().contains(bike.getFrameType()));
        if(bikeSearchDTO.getElectric() != null)
            bikes = bikes.filter(bike -> bikeSearchDTO.getElectric() == bike.isEbike());

        return bikes.collect(Collectors.toList());
    }

    abstract List<BicycleDTO> allBikes();
}
