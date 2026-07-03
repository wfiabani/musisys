package br.com.band.band.profissionais.infrastructure.web;

import br.com.band.band.profissionais.application.ProfissionaisService;
import br.com.band.band.profissionais.application.dto.ProfissionalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionaisService profissionaisService;

    public ProfissionalController(ProfissionaisService profissionaisService) {
        this.profissionaisService = profissionaisService;
    }

    @GetMapping
    public List<ProfissionalDTO> listProfissionais() {
        return profissionaisService.listAllProfissionais();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createProfissional(@RequestBody CreateProfissionalRequest request) {
        return profissionaisService.createProfissional(
                request.name(), request.role(), request.description(), request.isDefault());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfissional(@PathVariable UUID id, @RequestBody UpdateProfissionalRequest request) {
        profissionaisService.updateProfissional(
                id, request.name(), request.role(), request.description(), request.isDefault());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfissional(@PathVariable UUID id) {
        profissionaisService.deleteProfissional(id);
    }

    record CreateProfissionalRequest(String name, String role, String description, boolean isDefault) {}

    record UpdateProfissionalRequest(String name, String role, String description, boolean isDefault) {}
}
