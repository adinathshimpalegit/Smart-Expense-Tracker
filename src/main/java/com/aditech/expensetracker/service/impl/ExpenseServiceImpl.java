
package com.aditech.expensetracker.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.aditech.expensetracker.service.ExpenseService;
import com.aditech.expensetracker.repository.*;
import com.aditech.expensetracker.dto.*;
import com.aditech.expensetracker.entity.*;
import com.aditech.expensetracker.mapper.ExpenseMapper;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    
    @Autowired
    private ExpenseMapper expenseMapper;

    @Override
    public ExpenseDTO addExpense(ExpenseDTO dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Expense expense = expenseMapper.toEntity(dto);
        expense.setUser(user);
        Expense saved = expenseRepository.save(expense);
        return expenseMapper.toDTO(saved);
    }

    @Override
    public List<ExpenseDTO> getUserExpenses(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return expenseRepository.findByUser(user)
                .stream()
                .map(expenseMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setTitle(expenseDTO.getTitle());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDate(expenseDTO.getDate());

        Expense updated = expenseRepository.save(expense);

        return expenseMapper.toDTO(updated);
    }
    
    @Override
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expenseRepository.delete(expense);
    }
    @Override
    public List<ExpenseDTO> getExpensesByCategory(String category) {

        List<Expense> expenses = expenseRepository.findByCategory(category);

        return expenses.stream()
                .map(expenseMapper::toDTO)
                .toList();
    }
    @Override
    public Double getMonthlySummary(int month, int year) {

        Double total = expenseRepository.getMonthlyTotal(month, year);

        return total != null ? total : 0.0;
    }
}
