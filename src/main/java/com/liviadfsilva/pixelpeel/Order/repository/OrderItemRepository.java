package com.liviadfsilva.pixelpeel.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liviadfsilva.pixelpeel.Order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
