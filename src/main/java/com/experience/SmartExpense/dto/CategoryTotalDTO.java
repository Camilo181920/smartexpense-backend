package com.experience.SmartExpense.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Total de gastos agrupados por categoría")
public record CategoryTotalDTO(

        @Schema(
                description = "Nombre de la categoría",
                example = "Food"
        )
        String category,

        @Schema(
                description = "Total gastado",
                example = "560.75"
        )
        Double total

) {}