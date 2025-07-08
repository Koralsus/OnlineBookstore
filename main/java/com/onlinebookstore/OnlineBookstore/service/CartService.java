package com.onlinebookstore.OnlineBookstore.service;

import com.onlinebookstore.OnlineBookstore.model.Cart;
import com.onlinebookstore.OnlineBookstore.model.CartItem;
import java.util.List;

public interface CartService {
    Cart createCart(Integer userId);
    Cart getCartByUserId(Integer userId);
    CartItem addItemToCart(Integer userId, Integer bookId, int quantity);
    void removeItem(Integer cartItemId);
    void clearItems(Integer userId);
    List<CartItem> getCartItems(Integer userId);
    double calculateTotal(Integer cartId);
}
