package com.aerovik.nordic.model;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_id_seq")
    @SequenceGenerator(name = "aircraft_id_seq", sequenceName = "aircraft_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraft", orphanRemoval = true)
    private Set<Flight> flights = new HashSet<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
        flight.setAircraft(this);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
        flight.setAircraft(null);
    }

    public void removeFlights() {
        flights.forEach(flight -> flight.setAircraft(null));
        flights.clear();
    }

}
