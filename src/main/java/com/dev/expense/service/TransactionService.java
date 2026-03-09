package com.dev.expense.service;

import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<ExpenseTransaction> getTransaction(){
        return transactionRepository.findAll();
    }
}
