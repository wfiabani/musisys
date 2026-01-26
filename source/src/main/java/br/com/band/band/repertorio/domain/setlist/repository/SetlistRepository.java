package br.com.band.band.repertorio.domain.setlist.repository;

import br.com.band.band.repertorio.domain.setlist.model.Setlist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SetlistRepository {

    Setlist save(Setlist setlist);

    Optional<Setlist> findById(UUID id);

    List<Setlist> findAll();
}
