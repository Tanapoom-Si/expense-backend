package com.dev.expense.dto;

import com.dev.expense.model.ExpenseUser;
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
