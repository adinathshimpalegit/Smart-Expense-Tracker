
package com.aditech.expensetracker.mapper;

import com.aditech.expensetracker.entity.Expense;

import org.springframework.stereotype.Component;

import com.aditech.expensetracker.dto.ExpenseDTO;

@Component
public class ExpenseMapper {

    public Expense toEntity(ExpenseDTO dto) {
        Expense e = new Expense();
        e.setId(dto.getId());
        e.setTitle(dto.getTitle());
        e.setAmount(dto.getAmount());
        e.setCategory(dto.getCategory());
        e.setDate(dto.getDate());
        return e;
    }

    public ExpenseDTO toDTO(Expense e) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setAmount(e.getAmount());
        dto.setCategory(e.getCategory());
        dto.setDate(e.getDate());
        return dto;
    }
}
