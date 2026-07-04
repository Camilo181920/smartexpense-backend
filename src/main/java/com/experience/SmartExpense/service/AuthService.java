package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.LoginRequest;
import com.experience.SmartExpense.dto.LoginResponse;
import com.experience.SmartExpense.dto.RegisterRequest;
import com.experience.SmartExpense.dto.RegisterResponse;
import com.experience.SmartExpense.dto.AuthResponse;

public interface AuthService {

//    RegisterResponse register(RegisterRequest request);

//    LoginResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}