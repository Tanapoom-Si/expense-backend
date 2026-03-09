package com.dev.expense.repository;

import com.dev.expense.model.ExpenseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<ExpenseTransaction,Integer> {

}
