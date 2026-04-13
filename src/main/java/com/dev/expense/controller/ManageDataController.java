package com.dev.expense.controller;

import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.model.TransactionRequestDTO;
import com.dev.expense.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@Slf4j
public class ManageDataController {
    private final TransactionService transactionService;

    @GetMapping
    public List<ExpenseTransaction> getAllData(){
        return transactionService.getTransaction();
    }

    @PostMapping
    public ResponseEntity<ExpenseTransaction> addTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO){
        log.info("Received request to add transaction: {}", transactionRequestDTO);

        ExpenseTransaction saveTransaction = transactionService.processAndSaveTransaction(transactionRequestDTO);

        return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ExpenseTransaction> updateTRansaction(@RequestBody TransactionRequestDTO transactionRequestDTO){
        log.info("Received request to update transaction: {}", transactionRequestDTO);

        ExpenseTransaction updateTransaction = transactionService.updateTransaction(transactionRequestDTO);

        return new ResponseEntity<>(updateTransaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTransaction(@PathVariable int id){
        log.info("Received request delete transaction id: {}", id);

        transactionService.deleteTransaction(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
