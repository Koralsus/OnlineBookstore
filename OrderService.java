package com.onlinebookstore.OnlineBookstore.service;

import com.onlinebookstore.OnlineBookstore.model.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(Integer userId);
    List<Order> getOrdersByUser(Integer userId);
    Order getOrderById(Integer orderId);
}
