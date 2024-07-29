package com.aerovik.nordic.validation.impl;

import com.aerovik.nordic.repository.CustomerRepository;
import com.aerovik.nordic.validation.AvailableUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AvailableUsernameImpl implements ConstraintValidator<AvailableUsername, String> {
    private final CustomerRepository customerRepository;

    @Override
    public void initialize(AvailableUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !customerRepository.existsByUsername(s);
    }
}

