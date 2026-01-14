package com.track.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.track.Entity.Expense;
import com.track.repository.ExpenseRepository;

public class ExpenseService {
	
	@Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

}
