package com.dev.expense.repository;

import com.dev.expense.model.ExpenseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<ExpenseTransaction,Integer> {
    List<ExpenseTransaction> findByUser_Username(String username);
}
