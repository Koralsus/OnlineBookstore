package com.onlinebookstore.OnlineBookstore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="total_amount")
    private double totalAmount;
    @Column(name="order_date")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    public Order(){

    }

    public Integer getOrderId(){
        return this.orderId;
    }

    public User getUserId(){
        return this.user;
    }

    public LocalDateTime getOrderDate(){
        return this.orderDate;
    }

    public double getTotalAmount(){
        return this.totalAmount;
    }

    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
