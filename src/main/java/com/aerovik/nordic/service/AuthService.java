package com.aerovik.nordic.service;

import com.aerovik.nordic.payload.auth.LoginRequest;
import com.aerovik.nordic.payload.auth.LoginResponse;
import com.aerovik.nordic.payload.MessageResponse;
import com.aerovik.nordic.payload.auth.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponse loginUser(LoginRequest request);
    MessageResponse registerUser(RegistrationRequest request);
}
