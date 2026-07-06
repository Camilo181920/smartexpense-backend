package com.experience.SmartExpense.service;

import com.experience.SmartExpense.dto.ExpenseRequest;
import com.experience.SmartExpense.dto.ExpenseResponse;
import com.experience.SmartExpense.entity.Expense;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.repository.ExpenseRepository;
import com.experience.SmartExpense.repository.UserRepository;
import com.experience.SmartExpense.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Test
    void shouldCreateExpenseSuccessfully() {

        // Arrange
        ExpenseRequest request = new ExpenseRequest();
        request.setTitle("Lunch");
        request.setAmount(10.0);
        request.setCategory("Food");

        User user = new User();
        user.setEmail("user@test.com");

        when(userRepository.findByEmail("user@test.com"))
                .thenReturn(Optional.of(user));

        Expense savedExpense = Expense.builder()
                .id(1L)
                .title("Lunch")
                .amount(10.0)
                .category("Food")
                .user(user)
                .build();

        when(expenseRepository.save(any(Expense.class)))
                .thenReturn(savedExpense);

        // Act
        ExpenseResponse response = expenseService.createExpense(
                "user@test.com",
                request
        );

        // Assert
        assertNotNull(response);
        assertEquals("Lunch", response.getTitle());
        assertEquals(10.0, response.getAmount());
        assertEquals("Food", response.getCategory());

        verify(userRepository, times(1))
                .findByEmail("user@test.com");

        verify(expenseRepository, times(1))
                .save(any(Expense.class));
    }

    @Test
    void shouldReturnExpensesList() {

        when(expenseRepository.findByUserEmail("user@test.com"))
                .thenReturn(List.of());

        List<ExpenseResponse> result =
                expenseService.getExpenses("user@test.com");

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(expenseRepository, times(1))
                .findByUserEmail("user@test.com");
    }
}