package br.com.band.band.repertorio.domain.setlist.repository;

import br.com.band.band.repertorio.domain.setlist.model.Setlist;

import java.util.List;
import java.util.UUID;

public interface SetlistRepository {

    List<Setlist> findAll();

    Setlist findById(UUID id);

    void save(Setlist setlist);
}
