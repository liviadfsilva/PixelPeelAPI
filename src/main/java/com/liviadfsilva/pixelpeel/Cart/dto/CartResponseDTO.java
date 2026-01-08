package com.liviadfsilva.pixelpeel.Cart.dto;

import java.math.BigDecimal;
import java.util.List;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;

import lombok.Getter;

@Getter
public class CartResponseDTO {
    private final Long id;
    private final Long userId;
    private final List<CartItemResponseDTO> items;
    private final BigDecimal totalPrice;

    public CartResponseDTO(Cart cart) {
        this.id = cart.getId();
        this.userId = cart.getUser().getId();
        this.items = cart.getItems()
                .stream()
                .map(CartItemResponseDTO::new)
                .toList();
        this.totalPrice = items.stream()
                .map(CartItemResponseDTO::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
