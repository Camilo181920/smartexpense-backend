package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.UserRequestDTO;
import com.experience.SmartExpense.dto.UserResponseDTO;
import com.experience.SmartExpense.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        return service.getUserByEmail(email);
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
        return service.createUser(request);
    }
}