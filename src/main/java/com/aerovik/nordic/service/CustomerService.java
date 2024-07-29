package com.aerovik.nordic.service;

import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.payload.auth.RegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CustomerService {
    void saveNewCustomer(Customer customer);
    Customer createCustomerFromRequest(RegistrationRequest request);
    Customer findCustomerByUsername(String username);
    Customer findCustomerById(Long id);
    List<Customer> findAllCustomers();


}
