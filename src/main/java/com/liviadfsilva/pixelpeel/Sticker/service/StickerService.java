package com.liviadfsilva.pixelpeel.Sticker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.Sticker.model.Sticker;
import com.liviadfsilva.pixelpeel.Sticker.repository.StickerRepository;

@Service
public class StickerService {
    
    private final StickerRepository repository;

    public StickerService(StickerRepository repository) {
        this.repository = repository;
    }

    public List<Sticker> getAllStickers() {
        return repository.findAll();
    }

    public Optional<Sticker> getStickerById(Long id) {
        return repository.findById(id);
    }

    public Optional<Sticker> getStickerBySlug(String slug) {
        return repository.findBySlug(slug);
    }

    public Sticker createSticker(Sticker stickerDetails) {
        Sticker sticker = new Sticker();
        sticker.setTitle(stickerDetails.getTitle());
        sticker.setDescription(stickerDetails.getDescription());
        sticker.setCreator(stickerDetails.getCreator());
        sticker.setPrice(stickerDetails.getPrice());
        sticker.setSlug(stickerDetails.getSlug());
        sticker.setStoragePath(stickerDetails.getStoragePath());

        return repository.save(sticker);
    }

    public Sticker updateSticker(Long id, Sticker stickerDetails) {
        Sticker sticker = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sticker not found."));

        sticker.setTitle(stickerDetails.getTitle());
        sticker.setDescription(stickerDetails.getDescription());
        sticker.setCreator(stickerDetails.getCreator());
        sticker.setPrice(stickerDetails.getPrice());
        sticker.setSlug(stickerDetails.getSlug());
        sticker.setStoragePath(stickerDetails.getStoragePath());

        return repository.save(sticker);
    }

    public void deleteSticker(Long id) {
        repository.deleteById(id);
    }


}
