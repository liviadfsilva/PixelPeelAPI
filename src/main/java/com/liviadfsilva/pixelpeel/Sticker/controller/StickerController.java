package com.liviadfsilva.pixelpeel.Sticker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Sticker.model.Sticker;
import com.liviadfsilva.pixelpeel.Sticker.service.StickerService;

@RestController
@RequestMapping("/stickers")
public class StickerController {

    private final StickerService service;

    public StickerController(StickerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sticker> getAllStickers() {
        return service.getAllStickers();
    }

    @GetMapping("/{id}")
    public Sticker getStickerById(@PathVariable Long id) {
        return service.getStickerById(id)
        .orElseThrow(() -> new RuntimeException("Sticker not found."));
    }

    @GetMapping("/{slug}")
    public Sticker getStickerBySlug(@PathVariable String slug) {
        return service.getStickerBySlug(slug)
        .orElseThrow(() -> new RuntimeException("Sticker not found."));
    }

    @PostMapping
    public Sticker createSticker(@RequestBody Sticker sticker) {
        return service.createSticker(sticker);
    }

    @PatchMapping("/{id}")
    public Sticker updateSticker(@PathVariable Long id, @RequestBody Sticker sticker) {
        return service.updateSticker(id, sticker);
    }

    @DeleteMapping("/{id}")
    public void deleteSticker(@PathVariable Long id) {
        service.deleteSticker(id);
    }

}
