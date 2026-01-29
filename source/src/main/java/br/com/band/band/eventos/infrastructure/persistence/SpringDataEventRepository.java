package br.com.band.band.eventos.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataEventRepository
        extends JpaRepository<EventEntity, UUID> {
}
