package com.epam.polsl.bicycleapp.dataProviders.manufacturers;

import com.epam.polsl.bicycleapp.model.BicycleDTO;
import com.epam.polsl.bicycleapp.model.BicycleType;
import com.epam.polsl.bicycleapp.model.BikeSearchDTO;
import com.epam.polsl.bicycleapp.model.FrameType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RMController {
    private final RM rm = new RM();

    @GetMapping("bicycles/rm/search")
    public List<BicycleDTO> getBikes(
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "maximumWeight", required = false) String maximumWeight,
            @RequestParam(value = "minimumWeight", required = false) String minimumWeight,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "frameTypes", required = false) String frameTypes,
            @RequestParam(value = "electric", required = false) String electric
    ) {
        List<FrameType> frameTypesList = frameTypes == null ? null : Arrays.stream(frameTypes.split(","))
                .map(String::toUpperCase)
                .map(FrameType::valueOf)
                .toList();

        List<BicycleType> bicycleTypes = type == null ? null : Arrays.stream(type.split(","))
                .map(String::toUpperCase)
                .map(BicycleType::valueOf)
                .toList();

        BikeSearchDTO bikeSearchDTO = BikeSearchDTO.builder()
                .manufacturer(manufacturer)
                .maximumWeight(toDouble(maximumWeight))
                .minimumWeight(toDouble(minimumWeight))
                .allowedTypes(bicycleTypes)
                .frameTypes(frameTypesList)
                .electric(electric == null ? null : Boolean.valueOf(electric))
                .build();
        return rm.getBikes(bikeSearchDTO);
    }

    private Double toDouble(String str) {
        if(str == null)
            return null;
        return Double.valueOf(str);
    }
}
