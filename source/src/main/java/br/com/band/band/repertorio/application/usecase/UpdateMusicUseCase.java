package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.exception.MusicNotFoundException;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import br.com.band.band.repertorio.domain.model.Music;

import java.util.UUID;

public class UpdateMusicUseCase {

    private final MusicRepository repository;

    public UpdateMusicUseCase(MusicRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, String title, String author, String key, String description) {
        repository.findById(id).orElseThrow(() -> new MusicNotFoundException(id));
        repository.save(new Music(id, title, key, author, description));
    }
}
