package br.com.band.band.repertorio.domain.music.repository;

import br.com.band.band.repertorio.domain.music.model.Music;

import java.util.List;

public interface MusicRepository {

    List<Music> findAll();
}
