package br.com.band.band.repertorio.application.port.repository;

import br.com.band.band.repertorio.domain.model.Music;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MusicRepository {

    List<Music> findAll();

    Optional<Music> findById(UUID id);

    List<Music> findBySetlistId(UUID setlistId);

    void save(Music music);

    void deleteById(UUID id);

    boolean existsInAnySetlist(UUID musicId);

}