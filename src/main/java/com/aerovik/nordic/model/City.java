package com.aerovik.nordic.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_id_seq")
    @SequenceGenerator(name = "cities_id_seq", sequenceName = "cities_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
    private Set<Airport> airports = new HashSet<>();

    public void addAirport(Airport airport) {
        airports.add(airport);
        airport.setCity(this);
    }

    public void removeAirport(Airport airport) {
        airports.remove(airport);
        airport.setCity(null);
    }

    public void removeAirports() {
        airports.forEach(airport -> airport.setCity(null));
        airports.clear();
    }

}
