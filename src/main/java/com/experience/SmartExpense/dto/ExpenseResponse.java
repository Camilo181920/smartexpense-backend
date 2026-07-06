package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Información de un gasto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponse {

    @Schema(description = "Identificador del gasto", example = "1")
    private Long id;

    @Schema(description = "Título del gasto", example = "Compra supermercado")
    private String title;

    @Schema(description = "Monto del gasto", example = "125.50")
    private Double amount;

    @Schema(description = "Categoría", example = "Food")
    private String category;

    @Schema(
            description = "Fecha de creación",
            example = "2026-07-05T10:30:15"
    )
    private LocalDateTime createdAt;

}