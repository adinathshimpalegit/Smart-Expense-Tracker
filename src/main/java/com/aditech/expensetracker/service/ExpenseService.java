
package com.aditech.expensetracker.service;

import java.util.List;
import com.aditech.expensetracker.dto.ExpenseDTO;

public interface ExpenseService {
    ExpenseDTO addExpense(ExpenseDTO dto, String username);
    List<ExpenseDTO> getUserExpenses(String username);
}
