package com.liviadfsilva.pixelpeel.Order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Cart.model.CartItem;
import com.liviadfsilva.pixelpeel.Cart.service.CartService;
import com.liviadfsilva.pixelpeel.Order.model.Order;
import com.liviadfsilva.pixelpeel.Order.model.OrderItem;
import com.liviadfsilva.pixelpeel.Order.model.OrderStatus;
import com.liviadfsilva.pixelpeel.Order.model.PaymentStatus;
import com.liviadfsilva.pixelpeel.Order.repository.OrderRepository;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public Optional<Order> getOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }

    public Order createOrder(Long userId) {

        Cart cart = cartService.getCartByUserId(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);
        order.setTotal(calculateTotal(cart));

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            
            OrderItem oi = new OrderItem();
            oi.setOrder((order));
            oi.setSticker(cartItem.getSticker());
            oi.setQuantity(cartItem.getQuantity());
            oi.setPriceSnapshot(cartItem.getPrice());

            orderItems.add(oi);
        }

        order.setItems(orderItems);

        Order saved = orderRepository.save(order);

        cartService.clearCart(userId);

        return saved;
    }

    private BigDecimal calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .map(item ->
                        item.getSticker().getPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setPaymentStatus(paymentStatus);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    public Order markAsPaid(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getPaymentStatus() == PaymentStatus.PAID) {
            throw new RuntimeException("Order already paid");
        }

        order.setPaymentStatus(PaymentStatus.PAID);
        order.setOrderStatus(OrderStatus.ORDER_CONFIRMED);

        return orderRepository.save(order);
    }

    public Order cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        order.setOrderStatus(OrderStatus.ORDER_CANCELLED);
        order.setPaymentStatus(PaymentStatus.REFUNDED);

        return orderRepository.save(order);
    }
}
