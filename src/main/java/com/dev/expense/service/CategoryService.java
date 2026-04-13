package com.dev.expense.service;

import com.dev.expense.model.ExpenseCategory;
import com.dev.expense.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public ExpenseCategory saveCategory(ExpenseCategory category){
        return categoryRepository.save(category);
    }

    public void deleteByid(int id){
         categoryRepository.deleteById(id);
    }
}
