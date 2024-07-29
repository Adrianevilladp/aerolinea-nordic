package com.aerovik.nordic.dto;

import com.aerovik.nordic.enums.ERole;
import com.aerovik.nordic.model.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CustomerDetailsDTO {
    private String fullName;
    private String username;
    private String email;
    private String phone;

    public CustomerDetailsDTO(String firstName, String lastName,
                              String username, String email,
                              String phone) {
        this.fullName = fullName(firstName, lastName);
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    private String fullName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

}
