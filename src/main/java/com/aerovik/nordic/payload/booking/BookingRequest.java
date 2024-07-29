package com.aerovik.nordic.payload.booking;

import com.aerovik.nordic.validation.AvailableFlight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookingRequest (
        @NotBlank(message = "Booking type is required")
        String bookingType,

        @NotBlank(message = "Flight is required")
        @AvailableFlight
        String flightId,

        @NotNull(message = "Customers are required")
        String[] users
){
}
