package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.LoginRequest;
import com.experience.SmartExpense.dto.LoginResponse;
import com.experience.SmartExpense.dto.RegisterRequest;
import com.experience.SmartExpense.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}