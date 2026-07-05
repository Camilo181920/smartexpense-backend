package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.UserRequest;
import com.experience.SmartExpense.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    List<UserResponse> getUsers();

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);
}