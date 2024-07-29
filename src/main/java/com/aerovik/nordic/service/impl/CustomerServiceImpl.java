package com.aerovik.nordic.service.impl;

import com.aerovik.nordic.exception.NotFoundException;
import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.model.Payment;
import com.aerovik.nordic.payload.auth.RegistrationRequest;
import com.aerovik.nordic.repository.CustomerRepository;
import com.aerovik.nordic.repository.PaymentRepository;
import com.aerovik.nordic.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveNewCustomer(Customer customer) {
        savePayment(customer.getPayment());
        log.info("Customer [{}] was saved", customer.getUsername());
        Customer customerSaved = customerRepository.save(customer);
    }

    @Override
    public Customer createCustomerFromRequest(RegistrationRequest request) {
        Customer customerToRegister = new Customer();
        customerToRegister.setFirstName(request.firstName());
        customerToRegister.setLastName(request.lastName());
        customerToRegister.setUsername(request.username());
        customerToRegister.setEmail(request.email());
        customerToRegister.setPassword(passwordEncoder.encode(request.password()));
        customerToRegister.setPhone(request.phone());
        customerToRegister.setPayment(request.payment());

        return customerToRegister;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() -> {
            log.info("Customer with username {} not found", username);
            return new NotFoundException("Customer not found");
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> {
            log.info("Customer with id {} not found", id);
            return new NotFoundException("Customer not found");
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAllCustomers() {
        Optional<List<Customer>> customers = Optional.of(customerRepository.findAll());
        return customers.orElseThrow(() -> {
            log.info("No customers found");
            return new NotFoundException("No customers found");
        });
    }


    private void savePayment(Payment payment){
        if (Objects.isNull(payment.getId())) {
            paymentRepository.save(payment);
        }else{
            Optional<Payment> existingPayment = paymentRepository.findById(payment.getId());
            existingPayment.ifPresentOrElse(
                    foundPayment -> payment.setId(foundPayment.getId()),
                    () -> paymentRepository.save(payment)
            );
        }

    }

}
