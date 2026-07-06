package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Solicitud de inicio de sesión")
@Data
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @Schema(
            description = "Correo electrónico del usuario",
            example = "usuario@email.com"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(
            description = "Contraseña del usuario",
            example = "Password123"
    )
    private String password;
}