package com.track.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.track.Entity.Expense;
import com.track.Service.ExpenseService;


@RestController
@RequestMapping("/api/expense")
@CrossOrigin
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestParam Long userId,
                              @RequestBody Expense expense) {
        return expenseService.addExpense(expense, userId);
    }

    @GetMapping
    public List<Expense> getExpenses(@RequestParam Long userId) {
        return expenseService.getAllExpenses(userId);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,
                                 @RequestParam Long userId,
                                 @RequestBody Expense updatedExpense) {
        return expenseService.updateExpense(id, updatedExpense, userId);
    }
}

