package com.dev.expense.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReciveData {
    private String amount;
    private LocalDate date;
    private String title;
    private String type;
    private ExpenseUser user;
    private String note;
}
