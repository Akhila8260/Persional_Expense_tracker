package com.track.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.track.Entity.Budget;
import com.track.Entity.User;
import com.track.repository.BudgetRepository;
import com.track.repository.ExpenseRepository;
import com.track.repository.UserRepository;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepo;

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserRepository userRepository;

    // ✅ FIX: fetch user from DB
    public Budget saveBudget(Budget budget, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        budget.setUser(user);
        return budgetRepo.save(budget);
    }

    public List<Map<String, Object>> getBudgetStatus(Long userId) {

        List<Budget> budgets = budgetRepo.findByUserId(userId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Budget b : budgets) {
            Double spent = expenseRepo.getTotalExpenseByCategory(userId, b.getCategory());
            if (spent == null) spent = 0.0;

            Map<String, Object> data = new HashMap<>();
            data.put("category", b.getCategory());
            data.put("limit", b.getLimitAmount());
            data.put("spent", spent);
            data.put("percentage", (spent / b.getLimitAmount()) * 100);

            response.add(data);
        }

        return response;
    }
}
