package com.aerovik.nordic.controller;

import com.aerovik.nordic.dto.FlightDetailsDTO;
import com.aerovik.nordic.dto.mapper.FlightMapper;
import com.aerovik.nordic.payload.flight.FlightSearchRequest;
import com.aerovik.nordic.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/search")
@RestController
@RequiredArgsConstructor
public class SearchFlightController {
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @GetMapping("/all")
    public ResponseEntity<List<FlightDetailsDTO>> searchFlights() {
        List<FlightDetailsDTO> flightDetailsDTOList = flightService
                .findAll()
                .stream()
                .map(flightMapper::toFlightDetailsDTO)
                .toList();

        return ResponseEntity.ok(flightDetailsDTOList);
    }

    @GetMapping("/")
    public ResponseEntity<List<FlightDetailsDTO>> searchFlight(@RequestBody Optional<FlightSearchRequest> request) {
        List<FlightDetailsDTO> flightDetailsDTOList = flightService.searchFlights(request)
                .stream()
                .map(flightMapper::toFlightDetailsDTO)
                .toList();


        return ResponseEntity.ok(flightDetailsDTOList);
    }
}
