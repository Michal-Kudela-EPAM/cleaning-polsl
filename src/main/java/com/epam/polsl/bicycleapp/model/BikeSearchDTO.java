package com.epam.polsl.bicycleapp.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BikeSearchDTO {
    private String manufacturer;
    private Double maximumWeight;
    private Double minimumWeight;
    private List<BicycleType> allowedTypes;
    private List<FrameType> frameTypes;
    private Boolean electric;
}
