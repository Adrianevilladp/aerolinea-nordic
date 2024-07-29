package com.aerovik.nordic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_id_seq")
    @SequenceGenerator(name = "payments_id_seq", sequenceName = "payments_id_seq")
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true)
    //@CreditCardNumber(message = "Credit card number is invalid")
    private String cardNumber;

    @Column(name = "date_cc", nullable = false)
    @Future(message = "Credit card expiration date must be in the future")
    private LocalDate dateCC;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

}
