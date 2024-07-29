package com.aerovik.nordic.model;

import com.aerovik.nordic.enums.FlightStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "flights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_id_seq")
    @SequenceGenerator(name = "flight_id_seq", sequenceName = "flight_id_seq")
    private Long id;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Column(name = "gate", nullable = false)
    private String gate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flights_customers",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "flight", orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();


    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setFlight(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setFlight(null);
    }

    public void removeBookings() {
        bookings.forEach(booking -> booking.setFlight(null));
        bookings.clear();
    }

}
