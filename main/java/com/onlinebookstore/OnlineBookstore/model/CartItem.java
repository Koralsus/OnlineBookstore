package com.onlinebookstore.OnlineBookstore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Integer cartItemId;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book bookId;
    private int quantity;
    @Column(name="added_at")
    private LocalDateTime addedAt;

    public CartItem(){

    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public Cart getCartId() {
        return cart;
    }

    public Book getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setCartId(Cart cartId) {
        this.cart = cartId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
