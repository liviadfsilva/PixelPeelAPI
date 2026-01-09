package com.liviadfsilva.pixelpeel.Sticker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.Sticker.dto.StickerCreationDTO;
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

    public Sticker createSticker(StickerCreationDTO stickerCreationDTO) {
        Sticker sticker = new Sticker();
        sticker.setTitle(stickerCreationDTO.getTitle());
        sticker.setDescription(stickerCreationDTO.getDescription());
        sticker.setCreator(stickerCreationDTO.getCreator());
        sticker.setPrice(stickerCreationDTO.getPrice());
        sticker.setSlug(stickerCreationDTO.getSlug());
        sticker.setStoragePath(stickerCreationDTO.getStoragePath());

        return repository.save(sticker);
    }

    public Sticker updateSticker(Long id, StickerCreationDTO stickerCreationDTO) {
        Sticker sticker = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sticker not found."));

        sticker.setTitle(stickerCreationDTO.getTitle());
        sticker.setDescription(stickerCreationDTO.getDescription());
        sticker.setCreator(stickerCreationDTO.getCreator());
        sticker.setPrice(stickerCreationDTO.getPrice());
        sticker.setSlug(stickerCreationDTO.getSlug());
        sticker.setStoragePath(stickerCreationDTO.getStoragePath());

        return repository.save(sticker);
    }

    public void deleteSticker(Long id) {
        repository.deleteById(id);
    }
}
