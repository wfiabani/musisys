package br.com.band.band.profissionais.application.port.repository;

import br.com.band.band.profissionais.domain.model.Profissional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfissionalRepository {

    List<Profissional> findAll();

    Optional<Profissional> findById(UUID id);

    void save(Profissional profissional);

    void deleteById(UUID id);
}
