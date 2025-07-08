package com.onlinebookstore.OnlineBookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookstore.OnlineBookstore.model.OrderItem;
import com.onlinebookstore.OnlineBookstore.model.Order;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrder(Order order);
}
