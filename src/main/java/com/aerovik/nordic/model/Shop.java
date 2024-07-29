package com.aerovik.nordic.model;


import com.aerovik.nordic.enums.AirportZone;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shops_id_seq")
    @SequenceGenerator(name = "shops_id_seq", sequenceName = "shops_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "zone", nullable = false)
    private AirportZone airportZone;

    @ManyToMany(mappedBy = "shops")
    private Set<Airport> airports = new HashSet<>();
}
