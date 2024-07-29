package com.aerovik.nordic.model;

import com.aerovik.nordic.enums.BookingType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookings_id_seq")
    @SequenceGenerator(name = "bookings_id_seq", sequenceName = "bookings_id_seq")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingType bookingType;

    @Column(name = "checked_in", nullable = false)
    private boolean checkedIn;

    @Column(name = "booking_code", nullable = false, unique = true)
    private String bookingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "bookings_customers",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers = new HashSet<>();
    public Booking updateWith(Booking booking) {
        Booking updatedBooking = new Booking();
        updatedBooking.setId(this.id);
        updatedBooking.setBookingCode(this.bookingCode);
        updatedBooking.setBookingType(this.bookingType);
        updatedBooking.setCheckedIn(this.checkedIn);
        updatedBooking.setCreatedAt(this.createdAt);
        updatedBooking.setFlight(booking.getFlight());
        updatedBooking.getFlight().addBooking(updatedBooking);
        updatedBooking.setCustomers(this.customers);
        updatedBooking.getCustomers().addAll(booking.getCustomers());

        return updatedBooking;
    }


}
