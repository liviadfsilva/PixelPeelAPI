package com.liviadfsilva.pixelpeel.Cart.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Cart.dtos.CartResponseDTO;
import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public CartResponseDTO getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return new CartResponseDTO(cart);
    }

    @PostMapping("/{userId}/add")
    public CartResponseDTO addItem(
            @PathVariable Long userId,
            @RequestParam Long stickerId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.addItem(userId, stickerId, quantity);
        return new CartResponseDTO(cart);
    }

    @PatchMapping("/{userId}/update")
    public CartResponseDTO updateItem(
            @PathVariable Long userId,
            @RequestParam Long stickerId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.updateItem(userId, stickerId, quantity);
        return new CartResponseDTO(cart);
    }

    @DeleteMapping("/{userId}/remove")
    public CartResponseDTO removeItem(
            @PathVariable Long userId,
            @RequestParam Long stickerId
    ) {
        Cart cart = cartService.removeItem(userId, stickerId);
        return new CartResponseDTO(cart);
    }

    @DeleteMapping("/{userId}/clear")
    public CartResponseDTO clearCart(@PathVariable Long userId) {
        Cart cart = cartService.clearCart(userId);
        return new CartResponseDTO(cart);
    }
    
}
