package com.onlinebookstore.OnlineBookstore.service.implementations;


import com.onlinebookstore.OnlineBookstore.model.*;
import com.onlinebookstore.OnlineBookstore.repository.*;
import com.onlinebookstore.OnlineBookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceI implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order placeOrder(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUserId(user);
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0;
        Set<OrderItem> orderItems = new HashSet<>();

        for (CartItem cartItem : cart.getCartItems()) {
            Book book = bookRepository.findById(cartItem.getBookId().getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            if (book.getStocks() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for book: " + book.getTitle());
            }

            book.setStocks(book.getStocks() - cartItem.getQuantity());

            book.setSalesCount(book.getSalesCount() + cartItem.getQuantity());

            bookRepository.save(book);

            // Prepare order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order);
            orderItem.setBookId(book);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(book.getPrice());

            totalAmount += cartItem.getQuantity() * book.getPrice();
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // Clear cart after successful order
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    @Override
    public List<Order> getOrdersByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
