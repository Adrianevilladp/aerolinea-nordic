package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.FlightDetailsDTO;
import com.aerovik.nordic.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {
    public FlightDetailsDTO toFlightDetailsDTO(Flight flight) {
        return new FlightDetailsDTO(
                flight.getFlightNumber(),
                flight.getCurrency(),
                flight.getPrice(),
                flight.getDepartureDate(),
                flight.getArrivalDate(),
                flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                flight.getAircraft(),
                flight.getStatus(),
                flight.getGate()

        );

    }
}
