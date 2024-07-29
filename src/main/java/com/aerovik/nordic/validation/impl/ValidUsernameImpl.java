package com.aerovik.nordic.validation.impl;

import com.aerovik.nordic.validation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUsernameImpl implements ConstraintValidator<ValidUsername, String> {
    public static final String USERNAME_PATTERN =
            "^(?=.{2,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    @Override
    public void initialize(ValidUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
