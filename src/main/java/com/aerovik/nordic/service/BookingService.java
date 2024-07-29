package com.aerovik.nordic.service;

import com.aerovik.nordic.model.Booking;
import com.aerovik.nordic.payload.booking.BookingResponse;
import com.aerovik.nordic.payload.MessageResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    Booking createBooking(Booking booking);
    Booking createSingleBooking(Booking booking, String username);
    BookingResponse createBookingResponse(Booking booking);
    Optional<Booking> updateBooking(Long id, Booking booking);
    MessageResponse deleteBooking(Booking booking);
    Booking findBookingById(Long id);
    List<Booking> findAllBookings();
    Booking findBookingForCurrentUser(UserDetails userDetails);

}
