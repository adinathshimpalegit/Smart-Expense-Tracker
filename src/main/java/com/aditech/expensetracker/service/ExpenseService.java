
package com.aditech.expensetracker.service;

import java.util.List;
import com.aditech.expensetracker.dto.ExpenseDTO;
import com.aditech.expensetracker.entity.Expense;

public interface ExpenseService {
    ExpenseDTO addExpense(ExpenseDTO dto, String username);
    List<ExpenseDTO> getUserExpenses(String username);
    ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO);
    void deleteExpense(Long id);
    List<ExpenseDTO> getExpensesByCategory(String category);
    Double getMonthlySummary(int month, int year);
}
