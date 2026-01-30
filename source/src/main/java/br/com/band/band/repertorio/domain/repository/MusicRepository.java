package br.com.band.band.repertorio.domain.repository;

import br.com.band.band.repertorio.domain.model.Music;
import br.com.band.band.repertorio.domain.model.Setlist;

import java.util.List;
import java.util.UUID;

public interface MusicRepository {

    List<Music> findAll();

    List<Music> findBySetlistId(UUID setlistId);

}
