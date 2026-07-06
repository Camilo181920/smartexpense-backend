package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Solicitud para crear o actualizar un gasto")
@Data
public class ExpenseRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Schema(
            description = "Título del gasto",
            example = "Compra supermercado"
    )
    private String title;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    @Schema(
            description = "Monto del gasto",
            example = "125.50"
    )
    private Double amount;

    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    @Schema(
            description = "Categoría del gasto",
            example = "Food"
    )
    private String category;
}