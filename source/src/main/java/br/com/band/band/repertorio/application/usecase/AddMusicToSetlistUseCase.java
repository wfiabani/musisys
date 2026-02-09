package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;

import java.util.UUID;

public class AddMusicToSetlistUseCase {

    private final SetlistRepository setlistRepository;

    public AddMusicToSetlistUseCase(SetlistRepository setlistRepository) {
        this.setlistRepository = setlistRepository;
    }

    public void execute(UUID setlistId, UUID musicId) {
        Setlist setlist = setlistRepository.findById(setlistId).orElseThrow(() -> new RuntimeException("Setlist not found"));
        setlist.addMusic(musicId);
        setlistRepository.save(setlist);
    }
}
