package com.track.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.track.Entity.Income;
import com.track.Entity.User;
import com.track.repository.IncomeRepository;
import com.track.repository.UserRepository;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Correct way: fetch user from DB
    public Income addIncome(Income income, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        income.setUser(user);
        return incomeRepository.save(income);
    }

    public List<Income> getAllIncome(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    public Income updateIncome(Long incomeId, Income updatedIncome, Long userId) {

        Income existing = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        // 🔒 Security check
        if (!existing.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        existing.setSource(updatedIncome.getSource());
        existing.setAmount(updatedIncome.getAmount());

        return incomeRepository.save(existing);
    }

}
