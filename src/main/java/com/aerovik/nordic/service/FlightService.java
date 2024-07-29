package com.aerovik.nordic.service;

import com.aerovik.nordic.model.Flight;
import com.aerovik.nordic.payload.flight.FlightSearchRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface FlightService {

    List<Flight> findAll();

    Flight findById(Long id);

    List<Flight> searchFlights(Optional<FlightSearchRequest> request);


}
