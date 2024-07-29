package com.aerovik.nordic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AircraftDetailsDTO {
    private String name;
    private String model;
    private int capacity;

}
