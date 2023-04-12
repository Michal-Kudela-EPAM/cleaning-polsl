package com.epam.polsl.bicycleapp;

import com.epam.polsl.bicycleapp.model.BicycleDTO;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FindBikeServiceTest {

    @Mock
    private BicycleVendorInterface vendor;

    @Mock
    private CurrencyService currencyService;

    private FindBikeService findBikeService;

    @Test
    void getBikes() {
        findBikeService = new FindBikeService(List.of(vendor), currencyService);

        List<BicycleDTO> bicycles = List.of(bicycle());
        when(vendor.findBikes(any(), any(), any(), any(), any(), any())).thenReturn(bicycles);

        Parameters parameters = createParams()
                .manufacturer("ANY")
                .build();

        getBikesFromParams(parameters);

        verify(vendor).findBikes(any(), any(), any(), any(), any(), any());
        verifyNoInteractions(currencyService);
    }

    private Parameters.ParametersBuilder createParams() {
        return Parameters.builder();
    }

    private BicycleDTO bicycle() {
        return BicycleDTO
                .builder()
                .price(2000.0)
                .currency("PLN")
                .build();
    }

    private List<BicycleDTO> getBikesFromParams(Parameters parameters) {
        return findBikeService.findBikes(
                parameters.manufacturer,
                parameters.maximumWeight,
                parameters.minimumWeight,
                parameters.type,
                parameters.frameTypes,
                parameters.electric,
                parameters.maxPrice,
                parameters.minPrice,
                parameters.currency
        );
    }

    @Builder
    private static class Parameters{
        String manufacturer;
        String maximumWeight;
        String minimumWeight;
        String type;
        String frameTypes;
        String electric;
        String maxPrice;
        String minPrice;
        String currency;
    }
}