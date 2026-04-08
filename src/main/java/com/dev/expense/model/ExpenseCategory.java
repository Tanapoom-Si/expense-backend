package com.dev.expense.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense_category")
@Data
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int category_id;
    private String category_type;
    private String category_name;
}
