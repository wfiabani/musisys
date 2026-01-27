package br.com.band.band.repertorio.application.setlist.usecase;

import br.com.band.band.repertorio.domain.setlist.model.Setlist;
import br.com.band.band.repertorio.domain.setlist.repository.SetlistRepository;

import java.util.UUID;

public class ReorderSetlistUseCase {

    private final SetlistRepository repository;

    public ReorderSetlistUseCase(SetlistRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID setlistId, UUID musicId, int newPosition) {
        Setlist setlist = repository.findById(setlistId);
        setlist.moveMusic(musicId, newPosition);
        repository.save(setlist);
    }
}
