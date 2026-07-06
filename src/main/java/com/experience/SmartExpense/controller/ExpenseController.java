package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.config.swagger.CommonApiResponses;
import com.experience.SmartExpense.dto.CategoryTotalDTO;
import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponse;
import com.experience.SmartExpense.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/expenses")
@Tag(name = "Expenses", description = "Gestión de gastos personales del usuario autenticado")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @Operation(
            summary = "Crear gasto",
            description = "Crea un nuevo gasto asociado al usuario autenticado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Gasto creado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExpenseResponse.class)
            )
    )
    @CommonApiResponses
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody ExpenseRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.createExpense(
                        authentication.getName(),
                        request
                )
        );
    }

    @GetMapping
    @Operation(
            summary = "Obtener gastos",
            description = "Obtiene todos los gastos del usuario autenticado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de gastos obtenida correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = ExpenseResponse.class
                    )
            )
    )
    @CommonApiResponses
    public ResponseEntity<List<ExpenseResponse>> getExpenses(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.getExpenses(authentication.getName())
        );
    }

    @GetMapping("/summary")
    @Operation(
            summary = "Total de gastos",
            description = "Devuelve la suma total de gastos del usuario"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Total calculado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Double.class)
            )
    )
    @CommonApiResponses
    public ResponseEntity<Double> getSummary(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.getTotalExpenses(authentication.getName())
        );
    }

    @GetMapping("/by-category")
    @Operation(
            summary = "Gastos por categoría",
            description = "Agrupa los gastos del usuario por categoría"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Resumen por categoría obtenido correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = CategoryTotalDTO.class
                    )
            )
    )
    @CommonApiResponses
    public ResponseEntity<List<CategoryTotalDTO>> getByCategory(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.getExpensesByCategory(authentication.getName())
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar gasto",
            description = "Actualiza un gasto existente del usuario"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Gasto actualizado correctamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExpenseResponse.class)
            )
    )
    @CommonApiResponses
    public ResponseEntity<ExpenseResponse> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.updateExpense(
                        id,
                        request,
                        authentication.getName()
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar gasto",
            description = "Elimina un gasto del usuario"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Gasto eliminado correctamente"
    )
    @CommonApiResponses
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id,
            Authentication authentication
    ) {
        expenseService.deleteExpense(
                id,
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }
}
