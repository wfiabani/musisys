package br.com.band.band.eventos.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataEventRepository
        extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findBySetlistId(UUID id);
}
