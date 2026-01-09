package com.liviadfsilva.pixelpeel.Order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Order.dto.OrderResponseDTO;
import com.liviadfsilva.pixelpeel.Order.model.Order;
import com.liviadfsilva.pixelpeel.Order.model.OrderStatus;
import com.liviadfsilva.pixelpeel.Order.model.PaymentStatus;
import com.liviadfsilva.pixelpeel.Order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all-orders/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrdersByUserId(@PathVariable Long userId){

        List<OrderResponseDTO> orders = orderService.getAllOrdersByUserId(userId)
            .stream()
            .map(OrderResponseDTO::new)
            .toList();

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my-order/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {

        return orderService.getOrderById(orderId)
            .map(OrderResponseDTO::new)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create/{userId}")
    public Order createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);
    }

    @PatchMapping("/{orderId}/payment-status")
    public ResponseEntity<OrderResponseDTO> updatePaymentStatus(@PathVariable Long orderId, 
        @RequestParam PaymentStatus paymentStatus) {

        Order order = orderService.updatePaymentStatus(orderId, paymentStatus);
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PatchMapping("/{orderId}/order-status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus orderStatus) {

        Order order = orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PatchMapping("/{orderId}/pay")
    public ResponseEntity<OrderResponseDTO> payOrder(@PathVariable Long orderId) {

        Order order = orderService.markAsPaid(orderId);
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long orderId){

        Order order = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }
}
