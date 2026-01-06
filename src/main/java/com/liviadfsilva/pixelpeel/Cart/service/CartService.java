package com.liviadfsilva.pixelpeel.Cart.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.Cart.model.Cart;
import com.liviadfsilva.pixelpeel.Cart.model.CartItem;
import com.liviadfsilva.pixelpeel.Cart.repository.CartItemRepository;
import com.liviadfsilva.pixelpeel.Cart.repository.CartRepository;
import com.liviadfsilva.pixelpeel.Sticker.model.Sticker;
import com.liviadfsilva.pixelpeel.Sticker.repository.StickerRepository;
import com.liviadfsilva.pixelpeel.User.model.User;
import com.liviadfsilva.pixelpeel.User.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final StickerRepository stickerRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, StickerRepository stickerRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.stickerRepository = stickerRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> createCartForUser(userId));
    }

    private Cart createCartForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    public Cart addItem(Long userId, Long stickerId, int quantity) {
        Cart cart = getCartByUserId(userId);

        Sticker sticker = stickerRepository.findById(stickerId)
                .orElseThrow(() -> new RuntimeException("Sticker not found."));

        Optional<CartItem> existing = cartItemRepository.findByCartIdAndStickerId(cart.getId(), stickerId);

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        }
        else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setSticker(sticker);
            item.setQuantity(quantity);
            item.setPrice(sticker.getPrice());
            cartItemRepository.save(item);
        }

        return cart;
    }

    public Cart updateItem(Long userId, Long stickerId, int quantity) {
        Cart cart = getCartByUserId(userId);

        CartItem item = cartItemRepository.findByCartIdAndStickerId(cart.getId(), stickerId)
                .orElseThrow(() -> new RuntimeException("Item not found in cart."));

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return cart;
    }

    @Transactional
    public Cart removeItem(Long userId, Long stickerId) {
        Cart cart = getCartByUserId(userId);

        cartItemRepository.deleteByCartIdAndStickerId(cart.getId(), stickerId);

        return cart;
    }

    @Transactional
    public Cart clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cartItemRepository.deleteByCartId(cart.getId());
        return cart;
    }
}
