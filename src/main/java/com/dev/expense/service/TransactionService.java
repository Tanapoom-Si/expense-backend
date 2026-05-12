package com.dev.expense.service;

import com.dev.expense.dto.TransactionRequestDTO;
import com.dev.expense.model.ExpenseCategory;
import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.model.ExpenseUser;
import com.dev.expense.repository.TransactionRepository;
import com.dev.expense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;


    @Transactional
    public ExpenseTransaction processAndSaveTransaction(TransactionRequestDTO transactionDto){

        ExpenseUser user = userRepository.findByUsername(transactionDto.getUser().getUsername());
        if(user == null){
            throw new RuntimeException("User not found: " + transactionDto.getUser().getUsername());
        }

        //category save
        ExpenseCategory category = createCategoryFromDTO(transactionDto);
        ExpenseCategory savedCategory = categoryService.saveCategory(category);

        // transaction save
        ExpenseTransaction transaction = createTransactionFromDTO(transactionDto,savedCategory,user);
        return transactionRepository.save(transaction);
    }

    public ExpenseTransaction updateTransaction(TransactionRequestDTO transactionDto){

       ExpenseTransaction existingTransaction = transactionRepository.findById(transactionDto.getId())
               .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transactionDto.getId()));

       ExpenseUser user = userRepository.findByUsername(transactionDto.getUser().getUsername());
       if(user == null){
            throw new RuntimeException("User not found");
       }

       ExpenseCategory currentCategory = existingTransaction.getCategoryId();
       currentCategory.setCategory_type(transactionDto.getType());
       currentCategory.setCategory_name(transactionDto.getTitle());

       existingTransaction.setAmount(transactionDto.getAmount());
       existingTransaction.setUser(user);
       existingTransaction.setCreateDate(transactionDto.getDate());
       existingTransaction.setNote(transactionDto.getNote());

       return transactionRepository.save(existingTransaction);
    }

    @Transactional
    public void deleteTransaction(int id){
        if(!transactionRepository.existsById(id)){
            throw new RuntimeException("Transaction not found with id:" + id);
        }

        transactionRepository.deleteById(id);
    }

    public List<ExpenseTransaction> getDataByUser(ExpenseUser user){
        return transactionRepository.findByUser_Username(user.getUsername());
    }

    private ExpenseCategory createCategoryFromDTO(TransactionRequestDTO dto){
        ExpenseCategory cat = new ExpenseCategory();
        cat.setCategory_name(dto.getTitle());
        cat.setCategory_type(dto.getType());

        return cat;
    }

    private ExpenseTransaction createTransactionFromDTO(TransactionRequestDTO dto, ExpenseCategory cat , ExpenseUser user){
        ExpenseTransaction transaction = new ExpenseTransaction();
        transaction.setAmount(dto.getAmount());
        transaction.setCategoryId(cat);
        transaction.setUser(user);
        transaction.setCreateDate(dto.getDate());
        transaction.setNote(dto.getNote());

        return transaction;
    }


}
