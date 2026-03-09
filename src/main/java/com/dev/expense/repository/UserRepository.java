package com.dev.expense.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.expense.model.ExpenseUser;

@Repository
public interface UserRepository extends JpaRepository<ExpenseUser,String> {
    
    Optional<ExpenseUser> findByEmail(String email);

    boolean existsByUsername(String username);
}
