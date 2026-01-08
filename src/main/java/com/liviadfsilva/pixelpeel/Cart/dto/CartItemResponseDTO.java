package com.liviadfsilva.pixelpeel.Cart.dto;

import java.math.BigDecimal;

import com.liviadfsilva.pixelpeel.Cart.model.CartItem;

import lombok.Getter;

@Getter
public class CartItemResponseDTO {
    
    private final Long stickerId;
    private final BigDecimal price;
    private final int quantity;
    private final BigDecimal lineTotal;

    public CartItemResponseDTO(CartItem item) {
        this.stickerId = item.getSticker().getId();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.lineTotal = price.multiply(BigDecimal.valueOf(quantity));

    }
}
