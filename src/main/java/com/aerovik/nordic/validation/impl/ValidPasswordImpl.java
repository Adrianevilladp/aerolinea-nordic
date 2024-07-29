package com.aerovik.nordic.validation.impl;

import com.aerovik.nordic.validation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, String> {
    public static final String PASSWORD_PATTERN =
            "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,30}";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            // Código actual del método isValid

            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace(); // O utiliza un logger para imprimir el mensaje de la excepción
            return false; // O realiza el manejo de error adecuado según tus necesidades
        }
    }
}
