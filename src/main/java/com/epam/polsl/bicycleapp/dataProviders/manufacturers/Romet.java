package com.epam.polsl.bicycleapp.dataProviders.manufacturers;

import com.epam.polsl.bicycleapp.dataProviders.BicycleDTO;
import com.epam.polsl.bicycleapp.dataProviders.BicycleType;
import com.epam.polsl.bicycleapp.dataProviders.FrameType;

import java.util.List;

public class Romet extends BicycleRepository {
    @Override
    List<BicycleDTO> allBikes() {
        return List.of(
                bike("Rambler")
                        .weight(13)
                        .bicycleType(BicycleType.MTB)
                        .frameType(FrameType.HIGH_TUBE)
                        .ebike(false)
                        .price(3099)
                        .build(),

                bike("Wigry")
                        .weight(16)
                        .bicycleType(BicycleType.CITY)
                        .frameType(FrameType.LOW_TUBE)
                        .ebike(false)
                        .price(2049)
                        .build(),

                bike("Gazela")
                        .weight(16)
                        .bicycleType(BicycleType.TREKKING)
                        .frameType(FrameType.LOW_TUBE)
                        .ebike(false)
                        .price(2199)
                        .build(),

                bike("Gazela MM")
                        .weight(16)
                        .bicycleType(BicycleType.TREKKING)
                        .frameType(FrameType.LOW_TUBE)
                        .ebike(true)
                        .price(8999)
                        .build()
        );
    }

    private BicycleDTO.BicycleDTOBuilder bike(String name) {
        return BicycleDTO.builder()
                .manufacturer("ROMET")
                .model(name)
                .currency("PLN");
    }
}
