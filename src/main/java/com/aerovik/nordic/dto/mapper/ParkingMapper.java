package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.ParkingDetailsDTO;
import com.aerovik.nordic.model.Parking;
import org.springframework.stereotype.Component;

@Component
public class ParkingMapper {
    public ParkingDetailsDTO toParkingDTO(Parking parking) {
        return new ParkingDetailsDTO(
                parking.getName(),
                parking.getCurrency(),
                parking.getPrice(),
                parking.getQuality(),
                parking.getType()
        );
    }

}
