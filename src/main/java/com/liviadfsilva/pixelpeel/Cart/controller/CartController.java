package com.liviadfsilva.pixelpeel.Cart.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Auth.dto.AuthenticatedUserDTO;
import com.liviadfsilva.pixelpeel.Cart.dto.CartResponseDTO;
import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartResponseDTO getCart(@AuthenticationPrincipal AuthenticatedUserDTO user) {
        Cart cart = cartService.getCartByUserId(user.getId());
        return new CartResponseDTO(cart);
    }

    @PostMapping("/add")
    public CartResponseDTO addItem(
            @AuthenticationPrincipal AuthenticatedUserDTO user,
            @RequestParam Long stickerId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.addItem(user.getId(), stickerId, quantity);
        return new CartResponseDTO(cart);
    }

    @PatchMapping("/update")
    public CartResponseDTO updateItem(
            @AuthenticationPrincipal AuthenticatedUserDTO user,
            @RequestParam Long stickerId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.updateItem(user.getId(), stickerId, quantity);
        return new CartResponseDTO(cart);
    }

    @DeleteMapping("/remove")
    public void removeItem(@AuthenticationPrincipal AuthenticatedUserDTO user, @RequestParam Long stickerId) {
        cartService.removeItem(user.getId(), stickerId);
    }

    @DeleteMapping("/clear")
    public void clearCart(@AuthenticationPrincipal AuthenticatedUserDTO user) {
        cartService.clearCart(user.getId());
    }
    
}
