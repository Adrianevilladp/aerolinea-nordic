package com.aerovik.nordic.validation.impl;

import com.aerovik.nordic.repository.CustomerRepository;
import com.aerovik.nordic.validation.AvailableEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvailableEmailImpl implements ConstraintValidator<AvailableEmail, String> {
    private final CustomerRepository customerRepository;

    @Override
    public void initialize(AvailableEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !customerRepository.existsByEmail(s);
    }
}
