package com.experience.SmartExpense.service.impl;

import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponseDTO;
import com.experience.SmartExpense.dto.CategoryTotalDTO;
import com.experience.SmartExpense.entity.Expense;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.exception.ResourceNotFoundException;
import com.experience.SmartExpense.repository.ExpenseRepository;
import com.experience.SmartExpense.repository.UserRepository;
import com.experience.SmartExpense.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseResponseDTO createExpense(String userEmail, ExpenseRequest request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(request.getCategory())
                .user(user)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return mapToDTO(savedExpense);
    }

    @Override
    public List<ExpenseResponseDTO> getExpenses(String userEmail) {

        return expenseRepository.findByUserEmail(userEmail)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public Double getTotalExpenses(String email) {

        Double total = expenseRepository.getTotalByUserEmail(email);
        return total != null ? total : 0.0;
    }

    @Override
    public List<CategoryTotalDTO> getExpensesByCategory(String email) {
        return expenseRepository.getTotalByCategory(email);
    }

    @Override
    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequest request, String userEmail) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (!expense.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to this expense");
        }

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());

        Expense updated = expenseRepository.save(expense);

        return mapToDTO(updated);
    }

    @Override
    public void deleteExpense(Long id, String userEmail) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (!expense.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized access to this expense");
        }

        expenseRepository.delete(expense);
    }

    private ExpenseResponseDTO mapToDTO(Expense expense) {

        return ExpenseResponseDTO.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .createdAt(expense.getCreatedAt())
                .build();
    }
}