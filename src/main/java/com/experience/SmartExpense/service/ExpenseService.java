package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponse;
import com.experience.SmartExpense.dto.CategoryTotalDTO;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse createExpense(String userEmail, ExpenseRequest request);

    List<ExpenseResponse> getExpenses(String userEmail);

    Double getTotalExpenses(String email);

    List<CategoryTotalDTO> getExpensesByCategory(String email);

    ExpenseResponse updateExpense(Long id, ExpenseRequest request, String userEmail);

    void deleteExpense(Long id, String userEmail);
}