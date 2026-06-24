package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.exception.MusicInUseException;
import br.com.band.band.repertorio.application.exception.MusicNotFoundException;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;

import java.util.UUID;

public class DeleteMusicUseCase {

    private final MusicRepository repository;

    public DeleteMusicUseCase(MusicRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID musicId) {
        repository.findById(musicId)
                .orElseThrow(() -> new MusicNotFoundException(musicId));

        if (repository.existsInAnySetlist(musicId)) {
            throw new MusicInUseException(musicId);
        }

        repository.deleteById(musicId);
    }
}
