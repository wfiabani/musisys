package br.com.band.band.profissionais.application.usecase;

import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.profissionais.domain.model.Profissional;

import java.util.UUID;

public class CreateProfissionalUseCase {

    private final ProfissionalRepository repository;

    public CreateProfissionalUseCase(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public UUID execute(String name, String role, String description, boolean isDefault) {
        Profissional profissional = new Profissional(UUID.randomUUID(), name, role, description, isDefault);
        repository.save(profissional);
        return profissional.getId();
    }
}
