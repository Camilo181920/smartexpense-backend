package com.experience.SmartExpense.mapper;

import com.experience.SmartExpense.dto.ExpenseResponse;
import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.entity.Expense;

public class ExpenseMapper {

    private ExpenseMapper() {
    }

    public static ExpenseResponse toResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .createdAt(expense.getCreatedAt())
                .build();
    }

    public static Expense toEntity(ExpenseRequest request) {

        return Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(request.getCategory())
                .build();
    }
}