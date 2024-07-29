package com.aerovik.nordic.payload.flight;

import java.time.LocalDateTime;

public record FlightSearchRequest (
        String flightNumber,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        String departureAirportName,
        String arrivalAirportName
){
}
