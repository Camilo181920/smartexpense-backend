package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del usuario")
public class UserResponse {

    @Schema(description = "Identificador del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre", example = "Juan")
    private String firstName;

    @Schema(description = "Apellido", example = "Pérez")
    private String lastName;

    @Schema(description = "Correo electrónico", example = "juan@email.com")
    private String email;

    public UserResponse(Long id,
                        String firstName,
                        String lastName,
                        String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}