package com.dev.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.expense.repository.UserRepository;
import com.dev.expense.model.ExpenseUser;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ExpenseUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<ExpenseUser> getUserByUsername(String username){
        return userRepository.findById(username);
    }

    public ExpenseUser saveUser(ExpenseUser user){
        return userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }
    
}