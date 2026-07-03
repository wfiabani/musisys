package br.com.band.band.profissionais.application.usecase;

import br.com.band.band.profissionais.application.exception.ProfissionalNotFoundException;
import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.profissionais.domain.model.Profissional;

import java.util.UUID;

public class UpdateProfissionalUseCase {

    private final ProfissionalRepository repository;

    public UpdateProfissionalUseCase(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, String name, String role, String description, boolean isDefault) {
        repository.findById(id).orElseThrow(() -> new ProfissionalNotFoundException(id));
        repository.save(new Profissional(id, name, role, description, isDefault));
    }
}
