package com.onlinebookstore.OnlineBookstore.controller;

import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserInfoDTO;
import com.onlinebookstore.OnlineBookstore.model.Cart;
import com.onlinebookstore.OnlineBookstore.model.CartItem;
import com.onlinebookstore.OnlineBookstore.model.User;
import com.onlinebookstore.OnlineBookstore.service.CartService;
import com.onlinebookstore.OnlineBookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    private UserInfoDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        return userService.getCurrentUser(usernameOrEmail);
    }

    @PostMapping("/create")
    public Cart createCart() {
        UserInfoDTO currentUser = getCurrentUser();
        return cartService.createCart(currentUser.getUserId());
    }

    @PostMapping("/add")
    public CartItem addBookToCart(@RequestParam Integer bookId, @RequestParam int quantity) {
        UserInfoDTO currentUser = getCurrentUser();
        return cartService.addItemToCart(currentUser.getUserId(), bookId, quantity);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public void removeBookFromCart(@PathVariable Integer cartItemId) {
        cartService.removeItem(cartItemId);
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        UserInfoDTO currentUser = getCurrentUser();
        return cartService.getCartItems(currentUser.getUserId());
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        UserInfoDTO currentUser = getCurrentUser();
        cartService.clearItems(currentUser.getUserId());
    }

    @GetMapping("/total")
    public double getCartTotalAmount() {
        UserInfoDTO currentUser = getCurrentUser();
        Cart cart = cartService.getCartByUserId(currentUser.getUserId());
        return cartService.calculateTotal(cart.getCartId());
    }
}
