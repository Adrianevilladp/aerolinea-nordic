package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.AircraftDetailsDTO;
import com.aerovik.nordic.model.Aircraft;
import org.springframework.stereotype.Component;

@Component
public class AircraftMapper {
    public AircraftDetailsDTO toAircraftDetailsDTO(Aircraft aircraft) {
        return new AircraftDetailsDTO(
                aircraft.getName(),
                aircraft.getModel(),
                aircraft.getCapacity()
        );

    }
}
