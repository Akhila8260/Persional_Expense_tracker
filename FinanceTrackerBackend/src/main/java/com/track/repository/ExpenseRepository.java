package com.track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.track.Entity.Expense;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId AND e.title = :category")
	Double getTotalExpenseByCategory(Long userId, String category);
	
	@Query("SELECT e.title, SUM(e.amount) FROM Expense e GROUP BY e.title")
	List<Object[]> getExpenseByCategory();

	@Query("SELECT SUM(e.amount) FROM Expense e")
	Double getTotalExpense();


}
