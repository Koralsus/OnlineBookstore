package com.onlinebookstore.OnlineBookstore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Integer cartId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems;

    public Cart(){

    }

    public Integer getCartId() {
        return this.cartId;
    }

    public User getUserId() {
        return this.user;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
