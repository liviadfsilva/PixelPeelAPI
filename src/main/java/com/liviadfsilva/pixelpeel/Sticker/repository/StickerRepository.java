package com.liviadfsilva.pixelpeel.Sticker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liviadfsilva.pixelpeel.Sticker.model.Sticker;

public interface StickerRepository extends JpaRepository<Sticker, Long>{

    Optional<Sticker> findBySlug(String slug);
    
}
