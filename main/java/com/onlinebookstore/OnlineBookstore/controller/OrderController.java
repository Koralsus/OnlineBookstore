package com.onlinebookstore.OnlineBookstore.controller;

import com.onlinebookstore.OnlineBookstore.model.Order;
import com.onlinebookstore.OnlineBookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestParam Integer userId){
        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Integer userId){
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer orderId){
        return orderService.getOrderById(orderId);
    }
}
