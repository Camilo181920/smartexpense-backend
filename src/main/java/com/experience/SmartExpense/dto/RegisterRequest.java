package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Solicitud de registro de usuario")
public class RegisterRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    @Schema(
            description = "Nombre del usuario",
            example = "Juan"
    )
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Schema(
            description = "Apellido del usuario",
            example = "Pérez"
    )
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @Schema(
            description = "Correo electrónico del usuario",
            example = "juan.perez@email.com"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Schema(
            description = "Contraseña del usuario",
            example = "Password123"
    )
    private String password;
}