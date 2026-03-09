package com.dev.expense.controller;

import org.springframework.web.bind.annotation.RestController;
import com.dev.expense.service.UserService;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.dev.expense.model.ExpenseUser;

import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<ExpenseUser> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    public ExpenseUser createUser(@RequestBody ExpenseUser user) {
        return userService.saveUser(user); 
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @PutMapping("/{username}")
    public ExpenseUser putMethodName(@PathVariable String username, @RequestBody ExpenseUser userRequest) {
        Optional<ExpenseUser> userOpt = userService.getUserByUsername(username);
        String requestEmail = userRequest.getEmail();
        String requestRole = userRequest.getRole();
        String requestPassword = userRequest.getPassword();

        if (userOpt.isPresent()) {
            ExpenseUser existingUser = userOpt.get();
            existingUser.setEmail(requestEmail);
            existingUser.setRole(requestRole);

            if(requestPassword != null && !requestPassword.isEmpty()){
                existingUser.setPassword(requestPassword);
            }

            return userService.saveUser(existingUser);
        }else{
            throw new RuntimeException("ไม่พบผู้ใช่ชื่อ: " + username);
        }
    }
}


