package com.aerovik.nordic.payload.creater;

import com.aerovik.nordic.model.Booking;
import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.payload.booking.BookingResponse;

import java.math.BigDecimal;

public class BookingResponseCreation {
    public static BookingResponse createBookingResponse(Booking booking) {
        String arrivalAirport = booking
                .getFlight()
                .getArrivalAirport()
                .getName();
        String arrivalCity = booking
                .getFlight()
                .getArrivalAirport()
                .getCity()
                .getName();
        String departureAirport = booking
                .getFlight()
                .getDepartureAirport()
                .getName();
        String departureCity = booking
                .getFlight()
                .getDepartureAirport()
                .getCity()
                .getName();
        String[] passengers = booking
                .getCustomers()
                .stream()
                .map(Customer::getUsername)
                .toArray(String[]::new);
        String price = booking
                .getFlight()
                .getPrice()
                .multiply(BigDecimal.valueOf(booking.getCustomers().size()))
                .toString();

        return BookingResponse.builder()
                .arrivalAirport(arrivalAirport)
                .arrivalCity(arrivalCity)
                .departureAirport(departureAirport)
                .departureCity(departureCity)
                .numberFlight(booking.getFlight().getFlightNumber())
                .departureTime(booking.getFlight().getDepartureDate())
                .arrivalTime(booking.getFlight().getArrivalDate())
                .gate(booking.getFlight().getGate())
                .customers(passengers)
                .checkedIn(booking.isCheckedIn())
                .bookingCode(booking.getBookingCode())
                .bookingType(booking.getBookingType().name())
                .pricePaid(price)
                .build();
    }

}
