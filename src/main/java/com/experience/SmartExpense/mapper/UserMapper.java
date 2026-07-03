package com.experience.SmartExpense.mapper;

import com.experience.SmartExpense.dto.UserRequestDTO;
import com.experience.SmartExpense.dto.UserResponseDTO;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.entity.Role;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequestDTO dto) {

        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.USER)
                .build();
    }

    public static UserResponseDTO toResponse(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}