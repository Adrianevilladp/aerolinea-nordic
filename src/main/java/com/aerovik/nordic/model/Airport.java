package com.aerovik.nordic.model;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airports_id_seq")
    @SequenceGenerator(name = "airports_id_seq", sequenceName = "airports_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "airports_shops",
            joinColumns = @JoinColumn(name = "airport_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private Set<Shop> shops = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "airports_parkings",
            joinColumns = @JoinColumn(name = "airport_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_id"))
    private Set<Parking> parkings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departureAirport", orphanRemoval = true)
    private Set<Flight> departureFlights = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalAirport", orphanRemoval = true)
    private Set<Flight> arrivalFlights = new HashSet<>();

    public void addDepartureFlight(Flight flight) {
        departureFlights.add(flight);
        flight.setDepartureAirport(this);
    }

    public void removeDepartureFlight(Flight flight) {
        departureFlights.remove(flight);
        flight.setDepartureAirport(null);
    }

    public void removeDepartureFlights() {
        departureFlights.forEach(flight -> flight.setDepartureAirport(null));
        departureFlights.clear();
    }

    public void addArrivalFlight(Flight flight) {
        arrivalFlights.add(flight);
        flight.setArrivalAirport(this);
    }

    public void removeArrivalFlight(Flight flight) {
        arrivalFlights.remove(flight);
        flight.setArrivalAirport(null);
    }

    public void removeArrivalFlights() {
        arrivalFlights.forEach(flight -> flight.setArrivalAirport(null));
        arrivalFlights.clear();
    }


}
