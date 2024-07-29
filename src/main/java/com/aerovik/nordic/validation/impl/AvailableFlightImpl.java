package com.aerovik.nordic.validation.impl;

import com.aerovik.nordic.repository.FlightRepository;
import com.aerovik.nordic.validation.AvailableFlight;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvailableFlightImpl implements ConstraintValidator<AvailableFlight, String> {
    private final FlightRepository flightRepository;

    @Override
    public void initialize(AvailableFlight constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return flightRepository.existsById(Long.parseLong(s));
    }
}
