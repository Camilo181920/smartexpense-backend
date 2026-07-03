package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponseDTO;

import java.util.List;

public interface ExpenseService {

    ExpenseResponseDTO createExpense(String userEmail, ExpenseRequest request);

    List<ExpenseResponseDTO> getExpenses(String userEmail);

    Double getTotalExpenses(String email);

    List<Object[]> getExpensesByCategory(String email);

    ExpenseResponseDTO updateExpense(Long id, ExpenseRequest request, String userEmail);

    void deleteExpense(Long id, String userEmail);
}