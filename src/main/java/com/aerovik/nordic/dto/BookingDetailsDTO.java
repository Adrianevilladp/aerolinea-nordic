package com.aerovik.nordic.dto;

import com.aerovik.nordic.dto.mapper.CustomerMapper;
import com.aerovik.nordic.dto.mapper.FlightMapper;
import com.aerovik.nordic.enums.BookingType;
import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.model.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class BookingDetailsDTO {
    private static final FlightMapper flightMapper = new FlightMapper();
    private static final CustomerMapper customerMapper = new CustomerMapper();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    private BookingType bookingType;
    private boolean checkedIn;
    private String bookingCode;
    private FlightDetailsDTO flight;
    private Set<CustomerDetailsDTO> customers = new HashSet<>();

    public BookingDetailsDTO(LocalDateTime createdAt, BookingType bookingType,
                             boolean checkedIn, String bookingCode,
                             Flight flight, Set<Customer> customers) {
        this.createdAt = createdAt;
        this.bookingType = bookingType;
        this.checkedIn = checkedIn;
        this.bookingCode = bookingCode;
        this.flight = toFlightDTO(flight);
        this.customers = toCustomerDetails(customers);
    }

    private FlightDetailsDTO toFlightDTO(Flight flight) {
        return flightMapper.toFlightDetailsDTO(flight);
    }

    private Set<CustomerDetailsDTO> toCustomerDetails(Set<Customer> customers) {
        return customers
                .stream()
                .map(customerMapper::toCustomerDetails)
                .collect(Collectors.toSet());
    }

}
