package com.onlinebookstore.OnlineBookstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookstore.OnlineBookstore.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
