
package com.aditech.expensetracker.service.impl;

import lombok.RequiredArgsConstructor;
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

    @Override
    public ExpenseDTO addExpense(ExpenseDTO dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Expense expense = ExpenseMapper.toEntity(dto);
        expense.setUser(user);
        Expense saved = expenseRepository.save(expense);
        return ExpenseMapper.toDTO(saved);
    }

    @Override
    public List<ExpenseDTO> getUserExpenses(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return expenseRepository.findByUser(user)
                .stream()
                .map(ExpenseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
