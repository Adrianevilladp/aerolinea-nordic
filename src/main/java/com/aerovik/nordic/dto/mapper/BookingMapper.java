package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.BookingDetailsDTO;
import com.aerovik.nordic.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingDetailsDTO toBookingDetailsFromEntity(Booking booking) {
        return new BookingDetailsDTO(
                booking.getCreatedAt(),
                booking.getBookingType(),
                booking.isCheckedIn(),
                booking.getBookingCode(),
                booking.getFlight(),
                booking.getCustomers());
    }
}
