package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.UserRequestDTO;
import com.experience.SmartExpense.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequest);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByEmail(String email);
}