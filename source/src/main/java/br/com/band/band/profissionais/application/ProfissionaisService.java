package br.com.band.band.profissionais.application;

import br.com.band.band.profissionais.application.dto.ProfissionalDTO;
import br.com.band.band.profissionais.application.usecase.CreateProfissionalUseCase;
import br.com.band.band.profissionais.application.usecase.DeleteProfissionalUseCase;
import br.com.band.band.profissionais.application.usecase.ListAllProfissionaisUseCase;
import br.com.band.band.profissionais.application.usecase.UpdateProfissionalUseCase;

import java.util.List;
import java.util.UUID;

public class ProfissionaisService {

    private final ListAllProfissionaisUseCase listAllProfissionaisUseCase;
    private final CreateProfissionalUseCase createProfissionalUseCase;
    private final UpdateProfissionalUseCase updateProfissionalUseCase;
    private final DeleteProfissionalUseCase deleteProfissionalUseCase;

    public ProfissionaisService(
            ListAllProfissionaisUseCase listAllProfissionaisUseCase,
            CreateProfissionalUseCase createProfissionalUseCase,
            UpdateProfissionalUseCase updateProfissionalUseCase,
            DeleteProfissionalUseCase deleteProfissionalUseCase
    ) {
        this.listAllProfissionaisUseCase = listAllProfissionaisUseCase;
        this.createProfissionalUseCase = createProfissionalUseCase;
        this.updateProfissionalUseCase = updateProfissionalUseCase;
        this.deleteProfissionalUseCase = deleteProfissionalUseCase;
    }

    public List<ProfissionalDTO> listAllProfissionais() {
        return listAllProfissionaisUseCase.execute();
    }

    public UUID createProfissional(String name, String role, String description, boolean isDefault) {
        return createProfissionalUseCase.execute(name, role, description, isDefault);
    }

    public void updateProfissional(UUID id, String name, String role, String description, boolean isDefault) {
        updateProfissionalUseCase.execute(id, name, role, description, isDefault);
    }

    public void deleteProfissional(UUID id) {
        deleteProfissionalUseCase.execute(id);
    }
}
