package com.liviadfsilva.pixelpeel.Order.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Order.model.Order;
import com.liviadfsilva.pixelpeel.Order.model.PaymentStatus;
import com.liviadfsilva.pixelpeel.Order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @PostMapping("/create/{userId}")
    public Order createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);
    }

    @PutMapping("/{orderId}/status")
    public Order updateStatus(
            @PathVariable Long orderId,
            @RequestParam PaymentStatus status
    ) {
        return orderService.updatePaymentStatus(orderId, status);
    }
}
