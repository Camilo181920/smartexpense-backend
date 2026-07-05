package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.CategoryTotalDTO;
import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponse;
import com.experience.SmartExpense.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
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
    public ResponseEntity<List<ExpenseResponse>> getExpenses(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.getExpenses(authentication.getName())
        );
    }

    @GetMapping("/summary")
    public ResponseEntity<Double> getSummary(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.getTotalExpenses(authentication.getName())
        );
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<CategoryTotalDTO>> getByCategory(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                expenseService.getExpensesByCategory(authentication.getName())
        );
    }

    @PutMapping("/{id}")
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