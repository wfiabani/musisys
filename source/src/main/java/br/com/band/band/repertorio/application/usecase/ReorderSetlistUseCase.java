package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.domain.repository.SetlistRepository;

import java.util.UUID;

public class ReorderSetlistUseCase {

    private final SetlistRepository setlistRepository;

    public ReorderSetlistUseCase(SetlistRepository setlistRepository) {
        this.setlistRepository = setlistRepository;
    }

    public void execute(UUID setlistId, UUID musicId, int newPosition) {
        Setlist setlist = setlistRepository.findById(setlistId).orElseThrow(() -> new RuntimeException("Setlist not found"));
        setlist.moveMusic(musicId, newPosition);
        setlistRepository.save(setlist);
    }
}
