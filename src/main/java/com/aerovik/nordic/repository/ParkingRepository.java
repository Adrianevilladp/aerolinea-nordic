package com.aerovik.nordic.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aerovik.nordic.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long>{
}
