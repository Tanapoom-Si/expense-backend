package com.dev.expense.controller;

import com.dev.expense.model.ExpenseCategory;
import com.dev.expense.model.ExpenseTransaction;
import com.dev.expense.model.ExpenseUser;
import com.dev.expense.model.ReciveData;
import com.dev.expense.service.CategoryService;
import com.dev.expense.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/data")
public class ManageDataController {
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    public ManageDataController(TransactionService transactionService,CategoryService categoryService){
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ExpenseTransaction> getAllData(){
        return transactionService.getTransaction();
    }

    @PostMapping
    public void addTransaction(@RequestBody ReciveData data){
        System.out.println("title: " + data.getTitle());
        System.out.println("amount: " + data.getAmount());
        System.out.println("type: " + data.getType());
        System.out.println("date: " + data.getDate());
        System.out.println(data);
        //transactionService.saveTransaction(data);
        String type = data.getType();
        String title = data.getTitle();
        LocalDate date = data.getDate();
        ExpenseUser user = data.getUser();
        System.out.println("user : "+ user);
        String note = data.getNote();
        double amount = Double.parseDouble(data.getAmount());
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setCategory_type(type);
        expenseCategory.setCategory_name(title);
        categoryService.saveCategory(expenseCategory);
        // get catid after save cat and then save in transaction
        int catid = expenseCategory.getCategory_id();
        System.out.println("catid : " + catid);
        // fix playload add user for save in transaction
        ExpenseTransaction expenseTransaction = new ExpenseTransaction();
        expenseTransaction.setAmount(amount);
        expenseTransaction.setCategoryId(expenseCategory);
        expenseTransaction.setUser(user);
        expenseTransaction.setCreateDate(date);
        expenseTransaction.setNote(note);
        transactionService.saveTransaction(expenseTransaction);
    }
}
