package com.liviadfsilva.pixelpeel.Cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.liviadfsilva.pixelpeel.Cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    Optional<CartItem> findByCartUserId(Long userId);

    Optional<CartItem> findByCartIdAndStickerId(Long cartId, Long stickerId);

    void deleteByCartIdAndStickerId(Long cartId, Long stickerId);

    void deleteByCartId(Long cartId);

}
