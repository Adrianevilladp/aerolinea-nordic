package com.aerovik.nordic.service.impl;

import com.aerovik.nordic.exception.NotFoundException;
import com.aerovik.nordic.model.Booking;
import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.model.Flight;
import com.aerovik.nordic.payload.booking.BookingResponse;
import com.aerovik.nordic.payload.MessageResponse;
import com.aerovik.nordic.payload.creater.BookingResponseCreation;
import com.aerovik.nordic.repository.BookingRepository;
import com.aerovik.nordic.service.BookingService;
import com.aerovik.nordic.service.CustomerService;
import com.aerovik.nordic.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerService customerService;
    private final FlightService flightService;

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        booking.setCreatedAt(LocalDateTime.now());
        booking.setBookingCode(generateBookingCode());
        booking.setCheckedIn(true);
        checkIfCustomerIsAlreadyBooked(booking.getFlight().getId(), booking.getCustomers());
        booking.getFlight().addBooking(booking);
        Booking bookingSaved = bookingRepository.save(booking);
        log.info("Booking [{}] was saved", bookingSaved.getBookingCode());

        return bookingSaved;
    }

    @Override
    @Transactional
    public Booking createSingleBooking(Booking booking, String username) {
        Customer customerInDB = customerService.findCustomerByUsername(username);
        booking.setCustomers(Collections.singleton(customerInDB));

        return createBooking(booking);
    }

    @Override
    public BookingResponse createBookingResponse(Booking booking) {
        return BookingResponseCreation.createBookingResponse(booking);
    }


    @Override
    @Transactional
    public Optional<Booking> updateBooking(Long id, Booking booking) {
        Booking bookingInDB = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id [" + id + "] was not found"));
        Booking bookingUpdated = bookingInDB.updateWith(booking);

        return Optional.of(bookingRepository.save(bookingUpdated));
    }

    @Override
    @Transactional
    public MessageResponse deleteBooking(Booking booking) {
        Booking bookingInDB = bookingRepository.findById(booking.getId())
                .orElseThrow(() ->
                        new NotFoundException("Booking with id [" + booking.getId() + "] was not found"));
        bookingRepository.delete(bookingInDB);
        log.info("Booking [{}] was deleted", bookingInDB.getBookingCode());

        return new MessageResponse("Booking with id [" + booking.getId() + "] was deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id [" + id + "] was not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> findAllBookings() {
        Optional<List<Booking>> bookings = Optional.of(bookingRepository.findAll());

        return bookings.orElseThrow(() -> new NotFoundException("No bookings were found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findBookingForCurrentUser(UserDetails userDetails) {

        return bookingRepository.findBookingByUsernameCustomer(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("Booking for user [" + userDetails.getUsername() + "] was not found"));
    }

    private String generateBookingCode() {
        return UUID.randomUUID().toString().toUpperCase(Locale.ROOT).substring(0, 8);
    }

    private void checkIfCustomerIsAlreadyBooked(Long idFlight, Set<Customer> customers) {
        Flight flight = flightService.findById(idFlight);
        customers.forEach(customer -> {
            if (flight.getCustomers().contains(customer)) {
                throw new IllegalArgumentException("Customer [" + customer.getUsername() + "] is already booked on this flight");
            }
        });
    }
}