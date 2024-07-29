package com.aerovik.nordic.controller;

import com.aerovik.nordic.dto.CustomerDetailsDTO;
import com.aerovik.nordic.dto.mapper.CustomerMapper;
import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CustomerDetailsDTO>> findAllCustomer() {
        List<Customer> customer = customerService.findAllCustomers();
        List<CustomerDetailsDTO> customerDetailsDTO = customer
                .stream()
                .map(customerMapper::toCustomerDetails)
                .toList();


        return ResponseEntity.ok(customerDetailsDTO);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CustomerDetailsDTO> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        CustomerDetailsDTO customerDetailsDTO = customerMapper.toCustomerDetails(customer);

        return ResponseEntity.ok(customerDetailsDTO);
    }


}
