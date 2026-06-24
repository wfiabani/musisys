package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.exception.SetlistNotFoundException;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import br.com.band.band.repertorio.domain.model.Setlist;

import java.util.UUID;

public class RemoveMusicFromSetlistUseCase {

    private final SetlistRepository setlistRepository;

    public RemoveMusicFromSetlistUseCase(SetlistRepository setlistRepository) {
        this.setlistRepository = setlistRepository;
    }

    public void execute(UUID setlistId, UUID musicId) {
        Setlist setlist = setlistRepository.findById(setlistId)
                .orElseThrow(() -> new SetlistNotFoundException(setlistId));
        setlist.removeMusic(musicId);
        setlistRepository.save(setlist);
    }
}
