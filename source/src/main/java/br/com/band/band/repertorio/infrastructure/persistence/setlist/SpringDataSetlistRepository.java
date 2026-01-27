package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSetlistRepository
        extends JpaRepository<SetlistEntity, UUID> {
}