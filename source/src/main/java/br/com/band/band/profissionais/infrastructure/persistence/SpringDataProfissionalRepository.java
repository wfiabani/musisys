package br.com.band.band.profissionais.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataProfissionalRepository
        extends JpaRepository<ProfissionalEntity, UUID> {
}
