package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.LoginRequest;
import com.experience.SmartExpense.dto.RegisterRequest;
import com.experience.SmartExpense.dto.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}