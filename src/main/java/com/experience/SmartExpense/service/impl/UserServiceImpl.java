package com.experience.SmartExpense.service.impl;

import com.experience.SmartExpense.dto.UserRequestDTO;
import com.experience.SmartExpense.dto.UserResponseDTO;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.exception.ResourceNotFoundException;
import com.experience.SmartExpense.mapper.UserMapper;
import com.experience.SmartExpense.repository.UserRepository;
import com.experience.SmartExpense.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {

        User user = UserMapper.toEntity(userRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = repository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponseDTO> getUsers() {

        return repository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with email: " + email));

        return UserMapper.toResponse(user);
    }
}