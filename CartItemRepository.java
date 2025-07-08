package com.onlinebookstore.OnlineBookstore.repository;

import com.onlinebookstore.OnlineBookstore.model.CartItem;
import com.onlinebookstore.OnlineBookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
    List<CartItem> findByCart(Cart cart);
}
