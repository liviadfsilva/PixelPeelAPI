package com.liviadfsilva.pixelpeel.Sticker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Sticker.dto.StickerCreationDTO;
import com.liviadfsilva.pixelpeel.Sticker.dto.StickerResponseDTO;
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
    public ResponseEntity<List<StickerResponseDTO>> getAllStickers() {

        List<StickerResponseDTO> stickers = service.getAllStickers()
            .stream()
            .map(StickerResponseDTO::new)
            .toList();

        return ResponseEntity.ok(stickers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StickerResponseDTO> getStickerById(@PathVariable Long id) {

        return service.getStickerById(id)
                .map(StickerResponseDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<StickerResponseDTO> getStickerBySlug(@PathVariable String slug) {

        return service.getStickerBySlug(slug)
                .map(StickerResponseDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StickerResponseDTO> createSticker(@RequestBody StickerCreationDTO stickerCreationDTO) {

         Sticker sticker = service.createSticker(stickerCreationDTO);

        return ResponseEntity.ok(new StickerResponseDTO(sticker));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StickerResponseDTO> updateSticker(@PathVariable Long id, @RequestBody StickerCreationDTO stickerCreationDTO) {

        Sticker sticker = service.updateSticker(id, stickerCreationDTO);

        return ResponseEntity.ok(new StickerResponseDTO(sticker));
    }

    @DeleteMapping("/{id}")
    public void deleteSticker(@PathVariable Long id) {
        service.deleteSticker(id);
    }
}
