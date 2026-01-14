package com.track.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.track.Entity.Budget;
import com.track.Service.BudgetService;
@RestController
@RequestMapping("/api/budget")
@CrossOrigin
public class BudgetController {
	 @Autowired
	    private BudgetService budgetService;

	    @PostMapping
	    public Budget createBudget(
	            @RequestParam Long userId,
	            @RequestBody Budget budget) {

	        return budgetService.saveBudget(budget, userId);
	    }

	    @GetMapping("/{userId}")
	    public List<Map<String, Object>> getBudgetStatus(@PathVariable Long userId) {
	        return budgetService.getBudgetStatus(userId);
	    }

}
