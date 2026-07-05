package com.experience.SmartExpense.repository;

import com.experience.SmartExpense.entity.Expense;
import com.experience.SmartExpense.dto.CategoryTotalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserEmail(String email);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.email = :email")
    Double getTotalByUserEmail(@Param("email") String email);

    @Query("""
        SELECT new com.experience.SmartExpense.dto.CategoryTotalDTO(
            e.category,
            SUM(e.amount)
        )
        FROM Expense e
        WHERE e.user.email = :email
        GROUP BY e.category
    """)
    List<CategoryTotalDTO> getTotalByCategory(@Param("email") String email);
}