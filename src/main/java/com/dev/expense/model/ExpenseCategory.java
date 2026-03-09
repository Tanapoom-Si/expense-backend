package com.dev.expense.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "expense_category")
@Data
public class ExpenseCategory {
    @Id
    private int category_id;
    private String category_type;
    private String category_name;
}
