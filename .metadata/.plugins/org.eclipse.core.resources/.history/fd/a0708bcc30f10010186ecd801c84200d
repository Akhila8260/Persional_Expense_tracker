package com.track.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.track.Entity.Income;
import com.track.repository.IncomeRepository;
@Service
public class IncomeService {
	
	@Autowired
    private IncomeRepository incomeRepository;

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

}
