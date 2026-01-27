package br.com.band.band.repertorio.application.setlist.usecase;

import br.com.band.band.repertorio.domain.setlist.model.Setlist;
import br.com.band.band.repertorio.domain.setlist.repository.SetlistRepository;

import java.util.UUID;

public class CreateSetlistUseCase {

    private final SetlistRepository repository;

    public CreateSetlistUseCase(SetlistRepository repository) {
        this.repository = repository;
    }

    public UUID execute(String name) {
        Setlist setlist = new Setlist(UUID.randomUUID(), name);
        repository.save(setlist);
        return setlist.getId();
    }
}
