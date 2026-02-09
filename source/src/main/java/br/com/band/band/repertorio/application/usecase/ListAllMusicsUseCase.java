package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.domain.model.Music;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;

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
