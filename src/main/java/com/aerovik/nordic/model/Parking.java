package com.aerovik.nordic.model;

import com.aerovik.nordic.enums.ParkingQuality;
import com.aerovik.nordic.enums.ParkingType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parkings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkings_id_seq")
    @SequenceGenerator(name = "parkings_id_seq", sequenceName = "parkings_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quality", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingQuality quality;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingType type;

    @ManyToMany(mappedBy = "parkings")
    private Set<Airport> airports = new HashSet<>();


}
