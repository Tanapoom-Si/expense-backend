package com.dev.expense.controller;

import com.dev.expense.dto.LoginRequestDTO;
import com.dev.expense.model.ExpenseUser;
import com.dev.expense.model.UserRole;
import com.dev.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ExpenseUser> login(@RequestBody LoginRequestDTO request) {
        log.info("Login attempt for username: {}", request.getUsername());

        Optional<ExpenseUser> userOpt = userService.getUserByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ExpenseUser user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ExpenseUser request) {
        log.info("Register attempt for username: {}", request.getUsername());

        if (request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()
                || request.getEmail() == null || request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Username, email, and password are required");
        }

        if (userService.getUserByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }

        request.setRole(UserRole.user);

        ExpenseUser saved = userService.saveUser(request);
        saved.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
