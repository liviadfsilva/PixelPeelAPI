package com.liviadfsilva.pixelpeel.Cart.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Cart.service.CartService;

@RestController
@RequestMapping
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public Cart addItem(
            @PathVariable Long userId,
            @RequestParam Long stickerId,
            @RequestParam int quantity
    ) {
        return cartService.addItem(userId, stickerId, quantity);
    }

    @PutMapping("/{userId}/update")
    public Cart updateItem(
            @PathVariable Long userId,
            @RequestParam Long stickerId,
            @RequestParam int quantity
    ) {
        return cartService.updateItem(userId, stickerId, quantity);
    }

    @DeleteMapping("/{userId}/remove")
    public Cart removeItem(
            @PathVariable Long userId,
            @RequestParam Long stickerId
    ) {
        return cartService.removeItem(userId, stickerId);
    }

    @DeleteMapping("/{userId}/clear")
    public Cart clearCart(@PathVariable Long userId) {
        return cartService.clearCart(userId);
    }
    
}
