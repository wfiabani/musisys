package br.com.band.band.repertorio.application.music.usecase;

import br.com.band.band.repertorio.domain.music.model.Music;
import br.com.band.band.repertorio.domain.music.repository.MusicRepository;

import java.util.List;

public class ListAllMusicsUseCase {

    private final MusicRepository musicRepository;

    public ListAllMusicsUseCase(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> execute() {
        return musicRepository.findAll();
    }
}
