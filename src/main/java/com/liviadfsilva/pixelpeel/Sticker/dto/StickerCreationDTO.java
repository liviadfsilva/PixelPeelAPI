package com.liviadfsilva.pixelpeel.Sticker.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StickerCreationDTO {
    private String title;
    private String description;
    private BigDecimal price;
    private String creator;
    private String slug;
    private String storagePath;
}
