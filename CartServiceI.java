package com.onlinebookstore.OnlineBookstore.service.implementations;

import com.onlinebookstore.OnlineBookstore.model.Book;
import com.onlinebookstore.OnlineBookstore.model.Cart;
import com.onlinebookstore.OnlineBookstore.model.CartItem;
import com.onlinebookstore.OnlineBookstore.model.User;
import com.onlinebookstore.OnlineBookstore.repository.BookRepository;
import com.onlinebookstore.OnlineBookstore.repository.CartItemRepository;
import com.onlinebookstore.OnlineBookstore.repository.CartRepository;
import com.onlinebookstore.OnlineBookstore.repository.UserRepository;
import com.onlinebookstore.OnlineBookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceI implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Cart createCart(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User not found"));
        Cart cart = new Cart();
        cart.setUserId(user);
        cart.setCreatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(Integer userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user).orElseGet(()
                -> createCart(userId));
    }

    @Override
    public CartItem addItemToCart(Integer userId, Integer bookId, int quantity){
        Cart cart = getCartByUserId(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new RuntimeException("Book not found"));
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cart);
        cartItem.setBookId(book);
        cartItem.setQuantity(quantity);
        cartItem.setAddedAt(LocalDateTime.now());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeItem(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearItems(Integer userId) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> items = cartItemRepository.findByCart(cart);
        cartItemRepository.deleteAll(items);
    }

    @Override
    public List<CartItem> getCartItems(Integer userId) {
        Cart cart = getCartByUserId(userId);
        return cartItemRepository.findByCart(cart);
    }

    @Override
    public double calculateTotal(Integer cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getBookId().getPrice() * item.getQuantity())
                .sum();
    }
}
