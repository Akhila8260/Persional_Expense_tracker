package com.track.Service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.track.repository.ExpenseRepository;
import com.track.repository.IncomeRepository;

@Service
public class AnalyticsService {

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private IncomeRepository incomeRepo;

    public Map<String, Double> getSummary(Long userId) {
        Map<String, Double> map = new HashMap<>();
        map.put("totalExpense", expenseRepo.getTotalExpense(userId));
        map.put("totalIncome", incomeRepo.getTotalIncome(userId));
        return map;
    }

    public Map<String, Double> getExpenseByCategory(Long userId) {
        Map<String, Double> result = new HashMap<>();
        for (Object[] row : expenseRepo.getExpenseByCategory(userId)) {
            result.put((String) row[0], (Double) row[1]);
        }
        return result;
    }

}
