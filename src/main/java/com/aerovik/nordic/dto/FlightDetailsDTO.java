package com.aerovik.nordic.dto;

import com.aerovik.nordic.dto.mapper.AircraftMapper;
import com.aerovik.nordic.dto.mapper.AirportMapper;
import com.aerovik.nordic.enums.FlightStatus;
import com.aerovik.nordic.model.Aircraft;
import com.aerovik.nordic.model.Airport;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@NoArgsConstructor
public class FlightDetailsDTO {
    private static final AirportMapper airportMapper = new AirportMapper();
    private static final AircraftMapper aircraftMapper = new AircraftMapper();
    private String flightNumber;
    private String price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime departureDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime arrivalDate;
    private AirportDetailsDTO departureAirport;
    private AirportDetailsDTO arrivalAirport;
    private AircraftDetailsDTO aircraft;
    private String status;
    private String gate;

    public FlightDetailsDTO(String flightNumber, Currency currency, BigDecimal price,
                            LocalDateTime departureDate, LocalDateTime arrivalDate,
                            Airport departureAirport, Airport arrivalAirport,
                            Aircraft aircraft, FlightStatus status,
                            String gate) {

        this.flightNumber = flightNumber;
        this.price = String.format("%s %s", currency, price);
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAirport = toAirportDTO(departureAirport);
        this.arrivalAirport = toAirportDTO(arrivalAirport);
        this.aircraft = toAirportDTO(aircraft);
        this.status = status.name();
        this.gate = gate;
    }

    private AirportDetailsDTO toAirportDTO(Airport airport) {
        return airportMapper.toAirportDetailsDTO(airport);
    }

    private AircraftDetailsDTO toAirportDTO(Aircraft aircraft) {
        return aircraftMapper.toAircraftDetailsDTO(aircraft);
    }


    private int randomNumber(){
        int min = 10000000;
        int max = 99999999;

        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
