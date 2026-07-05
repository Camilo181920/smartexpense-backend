package com.experience.SmartExpense.mapper;

import com.experience.SmartExpense.dto.UserRequest;
import com.experience.SmartExpense.dto.UserResponse;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.entity.Role;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequest dto) {

        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.USER)
                .build();
    }

    public static UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}