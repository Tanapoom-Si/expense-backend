package com.dev.expense.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequestDTO {
    private Integer id;
    private Double amount;
    private LocalDate date;
    private String title;
    private String type;
    private ExpenseUser user;
    private String note;
}
