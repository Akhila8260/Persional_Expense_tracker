package com.track.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.track.Service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/summary")
    public Map<String, Double> summary() {
        return analyticsService.getSummary();
    }

    @GetMapping("/expense-category")
    public Map<String, Double> expenseByCategory() {
        return analyticsService.getExpenseByCategory();
    }
}
