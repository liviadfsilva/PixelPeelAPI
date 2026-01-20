package com.liviadfsilva.pixelpeel.Order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Auth.dto.AuthenticatedUserDTO;
import com.liviadfsilva.pixelpeel.Order.dto.OrderResponseDTO;
import com.liviadfsilva.pixelpeel.Order.model.Order;
import com.liviadfsilva.pixelpeel.Order.model.OrderStatus;
import com.liviadfsilva.pixelpeel.Order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrdersByUserId(@AuthenticationPrincipal AuthenticatedUserDTO user){

        List<OrderResponseDTO> orders = orderService.getAllOrdersByUserId(user.getId())
            .stream()
            .map(OrderResponseDTO::new)
            .toList();

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my-order/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId, 
        @AuthenticationPrincipal AuthenticatedUserDTO user) {

        Order order = orderService.getOrderByIdForUser(orderId, user.getId());
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@AuthenticationPrincipal AuthenticatedUserDTO user) {

        Order order = orderService.createOrder(user.getId());
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PatchMapping("/{orderId}/order-status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus orderStatus,
            @AuthenticationPrincipal AuthenticatedUserDTO user
        ) {

        Order order = orderService.updateOrderStatus(orderId, orderStatus, user.getId());
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PatchMapping("/{orderId}/pay")
    public ResponseEntity<OrderResponseDTO> payOrder(@PathVariable Long orderId, 
        @AuthenticationPrincipal AuthenticatedUserDTO user
    ) {

        Order order = orderService.markAsPaid(orderId, user.getId());
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long orderId, 
        @AuthenticationPrincipal AuthenticatedUserDTO user){

        Order order = orderService.cancelOrder(orderId, user.getId());
        return ResponseEntity.ok(new OrderResponseDTO(order));
    }
}
