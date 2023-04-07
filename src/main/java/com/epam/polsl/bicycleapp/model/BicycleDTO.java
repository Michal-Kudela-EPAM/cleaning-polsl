package com.epam.polsl.bicycleapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BicycleDTO {
    private String manufacturer;
    private String model;
    private double weight;
    private BicycleType bicycleType;
    private FrameType frameType;
    private boolean ebike;
    private double price;
    private String currency;
}
