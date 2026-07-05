package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.LoginRequest;
import com.experience.SmartExpense.dto.LoginResponse;
import com.experience.SmartExpense.dto.RegisterRequest;
import com.experience.SmartExpense.dto.RegisterResponse;
import com.experience.SmartExpense.dto.AuthResponse;
import com.experience.SmartExpense.service.AuthService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/register")
//    public RegisterResponse register(@RequestBody RegisterRequest request) {
//        return authService.register(request);
//    }
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//        return authService.login(request);
//    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}