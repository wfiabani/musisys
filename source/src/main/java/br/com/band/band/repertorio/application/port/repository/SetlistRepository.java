package br.com.band.band.repertorio.application.port.repository;

import br.com.band.band.repertorio.domain.model.Setlist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SetlistRepository {

    List<Setlist> findAll();

    Optional<Setlist> findById(UUID id);

    void save(Setlist setlist);

    void deleteById(UUID id);
}