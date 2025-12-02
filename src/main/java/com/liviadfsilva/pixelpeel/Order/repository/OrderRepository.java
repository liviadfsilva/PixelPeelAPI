package com.liviadfsilva.pixelpeel.Order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Optional<Cart> findByUserId(Long userId);
}
