package com.aerovik.nordic.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aerovik.nordic.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
