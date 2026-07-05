package com.experience.SmartExpense.service.impl;

import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponse;
import com.experience.SmartExpense.dto.CategoryTotalDTO;
import com.experience.SmartExpense.entity.Expense;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.exception.UnauthorizedException;
import com.experience.SmartExpense.mapper.ExpenseMapper;
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
    public ExpenseResponse createExpense(String userEmail, ExpenseRequest request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Expense expense = ExpenseMapper.toEntity(request);

        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.toResponse(savedExpense);
    }

    @Override
    public List<ExpenseResponse> getExpenses(String userEmail) {

        return expenseRepository.findByUserEmail(userEmail)
                .stream()
                .map(ExpenseMapper::toResponse)
                .toList();
    }

    @Override
    public Double getTotalExpenses(String email) {
        return expenseRepository.getTotalByUserEmail(email);
    }

    @Override
    public List<CategoryTotalDTO> getExpensesByCategory(String email) {
        return expenseRepository.getTotalByCategory(email);
    }

    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseRequest request, String userEmail) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (!expense.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedException("Unauthorized");
        }

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());

        Expense updated = expenseRepository.save(expense);

        return ExpenseMapper.toResponse(updated);
    }

    @Override
    public void deleteExpense(Long id, String userEmail) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (!expense.getUser().getEmail().equals(userEmail)) {
            throw new UnauthorizedException("Unauthorized");
        }

        expenseRepository.delete(expense);
    }

}