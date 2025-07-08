package com.onlinebookstore.OnlineBookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Integer orderItemId;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book bookId;
    private int quantity;
    @Column(name="unit_price")
    private double unitPrice;

    public OrderItem(){

    }

    public Integer getOrderItemId(){
        return this.orderItemId;
    }

    public Order getOrderId(){
        return this.order;
    }

    public Book getBookId(){
        return this.bookId;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public double getUnitPrice(){
        return this.unitPrice;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setOrderId(Order orderId) {
        this.order = orderId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
