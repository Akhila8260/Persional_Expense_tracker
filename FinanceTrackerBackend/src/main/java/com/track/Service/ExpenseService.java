package com.track.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.track.Entity.Expense;
import com.track.Entity.User;
import com.track.repository.ExpenseRepository;
import com.track.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public Expense addExpense(Expense expense, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public Expense updateExpense(Long expenseId, Expense updatedExpense, Long userId) {

        Expense existing = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // 🔒 Ownership validation
        if (!existing.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        existing.setTitle(updatedExpense.getTitle());
        existing.setAmount(updatedExpense.getAmount());

        return expenseRepository.save(existing);
    }

}
