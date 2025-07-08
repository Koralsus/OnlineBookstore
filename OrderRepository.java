package com.onlinebookstore.OnlineBookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookstore.OnlineBookstore.model.Order;
import com.onlinebookstore.OnlineBookstore.model.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User userId);
}
