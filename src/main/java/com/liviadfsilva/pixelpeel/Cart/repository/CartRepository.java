package com.liviadfsilva.pixelpeel.Cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.User.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUserId(Long userId);
}
