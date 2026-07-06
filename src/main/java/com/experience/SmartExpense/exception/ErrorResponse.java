package com.experience.SmartExpense.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Respuesta estándar para errores de la API")
public class ErrorResponse {

    @Schema(
            description = "Fecha y hora en la que ocurrió el error",
            example = "2026-07-05T18:30:15"
    )
    private LocalDateTime timestamp;

    @Schema(
            description = "Código HTTP del error",
            example = "404"
    )
    private int status;

    @Schema(
            description = "Descripción del error",
            example = "Expense not found"
    )
    private String message;

    public ErrorResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}