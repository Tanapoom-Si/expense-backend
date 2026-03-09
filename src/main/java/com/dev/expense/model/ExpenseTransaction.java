package com.dev.expense.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "expense_transaction")
@Data
public class ExpenseTransaction {
    @Id
    private int id;
    private double amount;
    private LocalDate createDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory categoryId;
    //private String username;
    private String note;
    @ManyToOne
    @JoinColumn(name = "username")
    private ExpenseUser user;
}
