package com.onlinebookstore.OnlineBookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookstore.OnlineBookstore.model.Cart;
import com.onlinebookstore.OnlineBookstore.model.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);
}
