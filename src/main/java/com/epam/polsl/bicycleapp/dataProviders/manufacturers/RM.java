package com.epam.polsl.bicycleapp.dataProviders.manufacturers;

import com.epam.polsl.bicycleapp.dataProviders.BicycleDTO;
import com.epam.polsl.bicycleapp.dataProviders.BicycleType;
import com.epam.polsl.bicycleapp.dataProviders.FrameType;

import java.util.List;

public class RM extends BicycleRepository {
    @Override
    List<BicycleDTO> allBikes() {
        return List.of(
                bike("Superdelite")
                        .weight(25)
                        .bicycleType(BicycleType.MTB)
                        .frameType(FrameType.HIGH_TUBE)
                        .price(8899)
                        .build(),

                bike("Homage")
                        .weight(30.0)
                        .bicycleType(BicycleType.TREKKING)
                        .frameType(FrameType.STEP_THROUGH)
                        .price(7779)
                        .build(),

                bike("Load 75")
                        .weight(45.0)
                        .bicycleType(BicycleType.CARGO)
                        .frameType(FrameType.LOW_TUBE)
                        .price(9279)
                        .build(),

                bike("Multitinker")
                        .weight(35.0)
                        .bicycleType(BicycleType.LONGTAIL)
                        .frameType(FrameType.STEP_THROUGH)
                        .price(6909)
                        .build()
        );
    }

    private BicycleDTO.BicycleDTOBuilder bike(String modelName) {
        return BicycleDTO.builder()
                .manufacturer("Riese & Muller")
                .model(modelName)
                .ebike(true)
                .currency("USD");
    }
}
