package com.liviadfsilva.pixelpeel.Order.dto;

import java.math.BigDecimal;

import com.liviadfsilva.pixelpeel.Order.model.OrderItem;

import lombok.Getter;
@Getter
public class OrderItemResponseDTO {
    private final Long stickerId;
    private final BigDecimal price;
    private final int quantity;
    private final BigDecimal lineTotal;

    public OrderItemResponseDTO(OrderItem item) {
        this.stickerId = item.getSticker().getId();
        this.price = item.getPriceSnapshot();
        this.quantity = item.getQuantity();
        this.lineTotal = price.multiply(BigDecimal.valueOf(quantity));
    }
}
