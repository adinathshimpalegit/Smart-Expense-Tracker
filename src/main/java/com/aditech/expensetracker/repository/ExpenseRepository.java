
package com.aditech.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditech.expensetracker.entity.*;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
}
