package com.aerovik.nordic.controller;

import com.aerovik.nordic.dto.BookingDetailsDTO;
import com.aerovik.nordic.dto.mapper.BookingMapper;
import com.aerovik.nordic.model.Booking;
import com.aerovik.nordic.payload.booking.BookingRequest;
import com.aerovik.nordic.payload.booking.BookingResponse;
import com.aerovik.nordic.payload.booking.BookingSingleRequest;
import com.aerovik.nordic.payload.mapper.BookingPayloadMapper;
import com.aerovik.nordic.service.BookingService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;
    private final BookingPayloadMapper bookingPayloadMapper;
    private final BookingMapper bookingMapper;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    // For two or more customers
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        Booking bookingToCreate = bookingPayloadMapper.toBookingFromBookingRequest(bookingRequest);
        Booking createdBooking = bookingService.createBooking(bookingToCreate);
        BookingResponse bookingResponse = bookingService.createBookingResponse(createdBooking);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBooking.getId())
                .toUri();

        return ResponseEntity.created(location).body(bookingResponse);
    }

    @PostMapping("/create/single")
    public ResponseEntity<BookingResponse> createSingleBooking(
            @Valid @RequestBody BookingSingleRequest request,
            HttpSession session) {
        String username = (String) session.getAttribute("username");
        Booking bookingToCreate = bookingPayloadMapper
                .toBookingFromBookingRequest(request);
        Booking createdBooking = bookingService.createSingleBooking(bookingToCreate, username);
        BookingResponse bookingResponse = bookingService.createBookingResponse(createdBooking);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBooking.getId())
                .toUri();

        return ResponseEntity.created(location).body(bookingResponse);
    }


    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookingDetailsDTO> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.findBookingById(id);
        BookingDetailsDTO bookingDetailsDTO = bookingMapper.toBookingDetailsFromEntity(booking);

        return ResponseEntity.ok(bookingDetailsDTO);
    }
}
