package com.aerovik.nordic.payload.booking;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookingResponse (
        String arrivalAirport,
        String arrivalCity,
        String departureAirport,
        String departureCity,
        String numberFlight,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String gate,
        String[] customers,
        boolean checkedIn,
        String bookingCode,
        String bookingType,
        String pricePaid
){
}
