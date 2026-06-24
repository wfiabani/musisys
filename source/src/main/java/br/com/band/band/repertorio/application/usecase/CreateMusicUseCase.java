package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import br.com.band.band.repertorio.domain.model.Music;

import java.util.UUID;

public class CreateMusicUseCase {

    private final MusicRepository repository;

    public CreateMusicUseCase(MusicRepository repository) {
        this.repository = repository;
    }

    public UUID execute(String title, String author, String key) {
        Music music = new Music(UUID.randomUUID(), title, key, author);
        repository.save(music);
        return music.getId();
    }
}
