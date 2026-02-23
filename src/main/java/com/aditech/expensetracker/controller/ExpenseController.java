
package com.aditech.expensetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import com.aditech.expensetracker.service.ExpenseService;
import com.aditech.expensetracker.dto.ExpenseDTO;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseDTO addExpense(@RequestBody ExpenseDTO dto, Principal principal) {
        return expenseService.addExpense(dto, principal.getName());
    }

    @GetMapping
    public List<ExpenseDTO> getExpenses(Principal principal) {
        return expenseService.getUserExpenses(principal.getName());
    }
}
