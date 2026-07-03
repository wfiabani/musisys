package br.com.band.band.profissionais.application.usecase;

import br.com.band.band.profissionais.application.dto.ProfissionalDTO;
import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;

import java.util.List;

public class ListAllProfissionaisUseCase {

    private final ProfissionalRepository repository;

    public ListAllProfissionaisUseCase(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public List<ProfissionalDTO> execute() {
        return repository.findAll().stream()
                .map(p -> new ProfissionalDTO(p.getId(), p.getName(), p.getRole(), p.getDescription(), p.isDefault()))
                .toList();
    }
}
