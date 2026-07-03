package com.experience.SmartExpense.controller;

import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponseDTO;
import com.experience.SmartExpense.service.ExpenseService;
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
    public ExpenseResponseDTO createExpense(
            @RequestBody ExpenseRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();
        return expenseService.createExpense(email, request);
    }

    @GetMapping
    public List<ExpenseResponseDTO> getExpenses(Authentication authentication) {

        String email = authentication.getName();
        return expenseService.getExpenses(email);
    }

    @GetMapping("/summary")
    public Double getSummary(Authentication authentication) {

        String email = authentication.getName();
        return expenseService.getTotalExpenses(email);
    }

    @GetMapping("/by-category")
    public List<Object[]> getByCategory(Authentication authentication) {

        String email = authentication.getName();
        return expenseService.getExpensesByCategory(email);
    }
}