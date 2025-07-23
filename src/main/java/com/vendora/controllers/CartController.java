package com.vendora.controllers;

import com.vendora.models.Cart;
import com.vendora.services.CartServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartServicesImpl cartServices;

    @PostMapping("/add")
    public ResponseEntity<Cart> addItem(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {

          Cart result=cartServices.addToCart(userId,productId,quantity);
          return ResponseEntity.ok().body(result);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart=cartServices.getCartByUserId(userId);
        return ResponseEntity.ok().body(cart);
    }


    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Cart> removeItem(@PathVariable Long userId, @PathVariable Long productId) {
       Cart cart=cartServices.removeFromCart(userId,productId);
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Cart> clearCart(@PathVariable Long userId) {
        Cart cart=cartServices.clearCart(userId);
        return ResponseEntity.ok().body(cart);
    }

}
