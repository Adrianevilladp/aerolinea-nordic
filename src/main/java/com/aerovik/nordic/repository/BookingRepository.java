package com.aerovik.nordic.repository;

import com.aerovik.nordic.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    @Query("SELECT b FROM Booking b JOIN b.customers c WHERE c.username = ?1")
    Optional<Booking> findBookingByUsernameCustomer(String username);
}
