package com.dev.expense.controller;

import com.dev.expense.dto.TransactionRequestDTO;
import com.dev.expense.dto.UserRequestDTO;
import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.service.TransactionService;
import com.dev.expense.service.UserService;
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
    private final UserService userService;

    @PostMapping("/filter")
    public ResponseEntity<List<ExpenseTransaction>> getAllData(@RequestBody UserRequestDTO request){
        if(request.getUser() == null || request.getUser().getUsername() == null){
            return ResponseEntity.badRequest().build();
        }

        return userService.getUserByUsername(request.getUser().getUsername())
                .map(user -> {
                    List<ExpenseTransaction> transactions = transactionService.getDataByUser(user);
                    return ResponseEntity.ok(transactions);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ExpenseTransaction> addTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO){
        log.info("Received request to add transaction: {}", transactionRequestDTO);
        ExpenseTransaction saveTransaction = transactionService.processAndSaveTransaction(transactionRequestDTO);

        return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ExpenseTransaction> updateTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO){
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
