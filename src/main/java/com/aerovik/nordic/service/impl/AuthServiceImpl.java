package com.aerovik.nordic.service.impl;

import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.model.Role;
import com.aerovik.nordic.payload.MessageResponse;
import com.aerovik.nordic.payload.auth.LoginRequest;
import com.aerovik.nordic.payload.auth.LoginResponse;
import com.aerovik.nordic.payload.auth.RegistrationRequest;
import com.aerovik.nordic.security.JwtTokenProvider;
import com.aerovik.nordic.service.AuthService;
import com.aerovik.nordic.service.CustomerService;
import com.aerovik.nordic.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;


    @Override
    @Transactional(readOnly = true)
    public LoginResponse loginUser(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()));
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        log.info("User {} logged in successfully!", request.username());
        String jwt = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(jwt);
    }

    @Override
    @Transactional
    public MessageResponse registerUser(RegistrationRequest request) {
        Customer customerToRegister = createCustomer(request);
        customerService.saveNewCustomer(customerToRegister);

        log.info("User {} registered successfully!", request.username());
        return new MessageResponse("User registered successfully!");
    }

    private Customer createCustomer(RegistrationRequest userRegistrationRequest){
        Set<Role> rolesToAssign = roleService.getRoleIfExist(userRegistrationRequest.roles());
        Customer newCustomer = customerService.createCustomerFromRequest(userRegistrationRequest);
        newCustomer.setRoles(rolesToAssign);

        return newCustomer;
    }

}
