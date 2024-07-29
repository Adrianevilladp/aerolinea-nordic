package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.AirportDetailsDTO;
import com.aerovik.nordic.model.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {
    public AirportDetailsDTO toAirportDetailsDTO(Airport airport) {
        return new AirportDetailsDTO(
                airport.getName(),
                airport.getCity(),
                airport.getCode(),
                airport.getShops(),
                airport.getParkings()
        );
    }
}
