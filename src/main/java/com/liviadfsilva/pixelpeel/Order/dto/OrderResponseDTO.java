package com.liviadfsilva.pixelpeel.Order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.liviadfsilva.pixelpeel.Order.model.Order;
import com.liviadfsilva.pixelpeel.Order.model.OrderStatus;
import com.liviadfsilva.pixelpeel.Order.model.PaymentStatus;

import lombok.Getter;

@Getter
public class OrderResponseDTO {
    private final Long id;
    private final OrderStatus orderStatus;
    private final PaymentStatus paymentStatus;
    private final BigDecimal total;
    private final List<OrderItemResponseDTO> items;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.orderStatus = order.getOrderStatus();
        this.paymentStatus = order.getPaymentStatus();
        this.total = order.getTotal();
        this.items = order.getItems().stream()
                .map(OrderItemResponseDTO::new)
                .toList();
    }
}
