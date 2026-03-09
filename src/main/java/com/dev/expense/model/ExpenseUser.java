package com.dev.expense.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "expense_user")
@Data
public class ExpenseUser {
    @Id
    private String username;

    private String password;
    private String role;
    private String email;
    private double balance;
}
