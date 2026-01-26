package br.com.band.band.repertorio.infrastructure.persistence.music;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataMusicRepository
        extends JpaRepository<MusicEntity, UUID> {
}
