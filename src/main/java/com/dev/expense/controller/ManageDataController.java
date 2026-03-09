package com.dev.expense.controller;

import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/data")
public class ManageDataController {
    private final TransactionService transactionService;

    public ManageDataController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<ExpenseTransaction> getAllData(){
        return transactionService.getTransaction();
    }
}
