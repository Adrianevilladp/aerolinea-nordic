package com.aerovik.nordic.payload.flight;

import com.aerovik.nordic.enums.FlightStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record FlightRequest (
        @Size(min = 8, max = 8, message = "flightNumber must be 8 characters long")
        String flightNumber,

        @NotNull(message = "currency must not be null")
        Currency currency,

        @NotNull(message = "price must not be null")
        @Positive(message = "price must be positive")
        BigDecimal price,

        @NotNull(message = "departureDate must not be null")
        @FutureOrPresent(message = "Departure date must be in the present or future")
        LocalDateTime departureDate,

        @NotNull(message = "arrivalDate must not be null")
        @FutureOrPresent(message = "Arrival date must be in the present or future")
        LocalDateTime arrivalDate,

        @NotNull(message = "departureAirportId must not be null")
        Long departureAirportId,

        @NotNull(message = "arrivalAirportId must not be null")
        Long arrivalAirportId,

        @NotNull(message = "aircraftId must not be null")
        Long aircraftId,

        @NotNull(message = "status must not be null")
        FlightStatus status,

        @NotNull(message = "gate must not be null")
        String gate


) {
}
