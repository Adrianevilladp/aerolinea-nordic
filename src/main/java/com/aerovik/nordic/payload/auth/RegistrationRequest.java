package com.aerovik.nordic.payload.auth;

import com.aerovik.nordic.model.Payment;
import com.aerovik.nordic.validation.AvailableEmail;
import com.aerovik.nordic.validation.AvailableUsername;
import com.aerovik.nordic.validation.ValidPassword;
import com.aerovik.nordic.validation.ValidUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record RegistrationRequest(
        @NotBlank(message = "First name is mandatory")
        @Pattern(regexp = "[A-Z][a-z]+", message = "First name must only contain alphabetic characters")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        @Pattern(regexp = "[A-Z][a-z]+", message = "Last name must only contain alphabetic characters")
        String lastName,

        @ValidUsername
        @AvailableUsername()
        @NotBlank(message = "Username is mandatory")
        String username,

        @AvailableEmail()
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @ValidPassword
        String password,

        @NotBlank(message = "Phone is mandatory")
        @Length(min = 10, max = 10, message = "Phone number must be 10 digits long")
        String phone,

        String[] roles,

        @NotNull(message = "Address is mandatory")
        Payment payment
) {
}
