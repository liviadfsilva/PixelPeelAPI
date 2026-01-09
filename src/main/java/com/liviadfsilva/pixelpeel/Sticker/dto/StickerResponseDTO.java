package com.liviadfsilva.pixelpeel.Sticker.dto;

import java.math.BigDecimal;

import com.liviadfsilva.pixelpeel.Sticker.model.Sticker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StickerResponseDTO {
    private final String title;
    private final String description;
    private final BigDecimal price;
    private final String creator;
    private final String slug;
    private final String storagePath;

    public StickerResponseDTO(Sticker sticker) {
        this.title = sticker.getTitle();
        this.description = sticker.getDescription();
        this.price = sticker.getPrice();
        this.creator = sticker.getCreator();
        this.slug = sticker.getSlug();
        this.storagePath = sticker.getStoragePath();
    }
}
