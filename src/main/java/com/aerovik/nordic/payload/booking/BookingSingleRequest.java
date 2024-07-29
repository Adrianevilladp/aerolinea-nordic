package com.aerovik.nordic.payload.booking;

import jakarta.validation.constraints.NotNull;

public record BookingSingleRequest (
        @NotNull(message = "Booking type is required")
        String bookingType,

        @NotNull(message = "Flight is required")
        String flightId
) {

}
