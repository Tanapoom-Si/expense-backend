package com.dev.expense.service;

import com.dev.expense.model.ExpenseCategory;
import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.model.ExpenseUser;
import com.dev.expense.repository.CategoryRepository;
import com.dev.expense.repository.TransactionRepository;
import com.dev.expense.repository.UserRepository;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<ExpenseTransaction> getTransaction(){
        return transactionRepository.findAll();
    }

    public ExpenseTransaction saveTransaction(ExpenseTransaction data){

        System.out.println("cat: " + data.getCategoryId());
        ExpenseCategory category = categoryRepository.findById(data.getCategoryId().getCategory_id()).orElseThrow(() -> new RuntimeException());

        ExpenseUser user = userRepository.findByUsername(data.getUser().getUsername());
        if(user == null){
            throw new RuntimeException("user not found: " + data.getUser().getUsername());
        }

        data.setCategoryId(category);
        data.setUser(user);

        return transactionRepository.save(data);
    }


}
