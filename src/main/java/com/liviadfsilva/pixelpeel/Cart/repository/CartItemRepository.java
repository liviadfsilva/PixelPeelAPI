package com.liviadfsilva.pixelpeel.Cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    Optional<Cart> findByUserId(Long userId);

    Optional<CartItem> findByCartIdAndStickerId(Long cartId, Long stickerId);

    Optional<CartItem> deleteByCartIdAndStickerId(Long cartId, Long stickerId);

    Optional<CartItem> deleteByCartId(Long cartId);

}
