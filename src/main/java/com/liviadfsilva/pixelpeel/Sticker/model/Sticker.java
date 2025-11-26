package com.liviadfsilva.pixelpeel.Sticker.model;

import java.math.BigDecimal;
import java.util.List;

import com.liviadfsilva.pixelpeel.Cart.model.CartItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stickers")
public class Sticker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String URL;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

}
