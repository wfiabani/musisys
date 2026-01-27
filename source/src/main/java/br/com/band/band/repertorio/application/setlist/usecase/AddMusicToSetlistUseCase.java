package br.com.band.band.repertorio.application.setlist.usecase;

import br.com.band.band.repertorio.domain.setlist.model.Setlist;
import br.com.band.band.repertorio.domain.setlist.repository.SetlistRepository;

import java.util.UUID;

public class AddMusicToSetlistUseCase {

    private final SetlistRepository repository;

    public AddMusicToSetlistUseCase(SetlistRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID setlistId, UUID musicId) {
        Setlist setlist = repository.findById(setlistId);
        setlist.addMusic(musicId);
        repository.save(setlist);
    }
}
