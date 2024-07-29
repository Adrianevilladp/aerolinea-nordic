package com.aerovik.nordic.payload.mapper;

import com.aerovik.nordic.enums.BookingType;
import com.aerovik.nordic.model.Booking;
import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.model.Flight;
import com.aerovik.nordic.payload.booking.BookingRequest;
import com.aerovik.nordic.payload.booking.BookingSingleRequest;
import com.aerovik.nordic.service.CustomerService;
import com.aerovik.nordic.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingPayloadMapper {
    private final FlightService flightService;
    private final CustomerService customerService;

    public Booking toBookingFromBookingRequest(BookingRequest request) {
        Booking booking = new Booking();
        Flight flightInDB = flightService.findById(Long.parseLong(request.flightId()));
        List<String> usernamesList = Arrays.stream(request.users()).toList();
        Set<Customer> customersInDB = usernamesList.stream()
                .map(customerService::findCustomerByUsername)
                .collect(Collectors.toSet());
        booking.setFlight(flightInDB);
        booking.setCustomers(customersInDB);
        booking.setBookingType(BookingType.fromString(request.bookingType()));

        return booking;
    }

    public Booking toBookingFromBookingRequest(BookingSingleRequest request) {
        Booking booking = new Booking();
        Flight flightInDB = flightService.findById(Long.parseLong(request.flightId()));
        booking.setFlight(flightInDB);
        booking.setBookingType(BookingType.fromString(request.bookingType()));

        return booking;
    }
}
