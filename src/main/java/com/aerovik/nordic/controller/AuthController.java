package com.aerovik.nordic.controller;

import com.aerovik.nordic.model.Customer;
import com.aerovik.nordic.payload.auth.LoginRequest;
import com.aerovik.nordic.payload.auth.LoginResponse;
import com.aerovik.nordic.payload.MessageResponse;
import com.aerovik.nordic.payload.auth.RegistrationRequest;
import com.aerovik.nordic.service.AuthService;
import com.aerovik.nordic.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegistrationRequest request) {
        MessageResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request,
                                                   HttpSession session) {
        session.setAttribute("username", request.username());
        LoginResponse response = authService.loginUser(request);
        return ResponseEntity.ok(response);
    }

}
