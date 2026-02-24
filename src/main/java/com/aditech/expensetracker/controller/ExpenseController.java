
package com.aditech.expensetracker.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseDTO expenseDTO) {

        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseDTO>> getByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                expenseService.getExpensesByCategory(category)
        );
    }
}
