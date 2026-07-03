package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.UserRequestDTO;
import com.experience.SmartExpense.dto.UserResponseDTO;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.mapper.UserMapper;
import com.experience.SmartExpense.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDTO> getUsers() {

        return service.getUsers()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {

        User user = service.getUserById(id);

        return UserMapper.toResponse(user);
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(Authentication authentication) {

        String email = authentication.getName();

        User user = service.getUserByEmail(email);

        return UserMapper.toResponse(user);
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {

        User user = UserMapper.toEntity(request);

        User savedUser = service.createUser(user);

        return UserMapper.toResponse(savedUser);
    }
}