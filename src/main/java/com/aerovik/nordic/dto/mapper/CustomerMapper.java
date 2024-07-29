package com.aerovik.nordic.dto.mapper;

import com.aerovik.nordic.dto.CustomerDetailsDTO;
import com.aerovik.nordic.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDetailsDTO toCustomerDetails(Customer customer) {
        return new CustomerDetailsDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
