package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de autenticación")
public record AuthResponse(

        @Schema(
                description = "Token JWT para autenticación",
                example = "eyJhbGciOiJIUzI1NiJ9..."
        )
        String token,

        @Schema(
                description = "Mensaje de la operación",
                example = "Login successful"
        )
        String message

) {}