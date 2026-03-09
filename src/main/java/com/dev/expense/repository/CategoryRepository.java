package com.dev.expense.repository;

import com.dev.expense.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ExpenseCategory,Integer> {
}
