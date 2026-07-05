package com.experience.SmartExpense.service.impl;

import com.experience.SmartExpense.dto.LoginRequest;
import com.experience.SmartExpense.dto.RegisterRequest;
import com.experience.SmartExpense.dto.AuthResponse;
import com.experience.SmartExpense.entity.Role;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.exception.EmailAlreadyExistsException;
import com.experience.SmartExpense.exception.InvalidCredentialsException;
import com.experience.SmartExpense.repository.UserRepository;
import com.experience.SmartExpense.security.JwtService;
import com.experience.SmartExpense.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String token = generateToken(user);

        return new AuthResponse(token, "User registered successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = generateToken(user);

        return new AuthResponse(token, "Login successful");
    }

    private String generateToken(User user) {
        return jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}