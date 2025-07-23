package com.vendora.services;

import com.vendora.models.Cart;
import org.springframework.http.ResponseEntity;

public interface CartServices {
    public Cart addToCart(Long userId, Long productId,int quantity);
    public Cart removeFromCart(Long userId, Long productId);
    public Cart clearCart(Long userId);
    public Cart getCartByUserId(Long userId);

}
