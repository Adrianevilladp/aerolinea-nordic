package com.aerovik.nordic.validation;

import com.aerovik.nordic.validation.impl.AvailableFlightImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AvailableFlightImpl.class)
public @interface AvailableFlight {
    String message() default "Flight isn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
