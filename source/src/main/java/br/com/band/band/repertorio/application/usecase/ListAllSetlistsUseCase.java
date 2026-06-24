package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.dto.SetlistSummaryDTO;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;

import java.util.List;

public class ListAllSetlistsUseCase {

    private final SetlistRepository repository;

    public ListAllSetlistsUseCase(SetlistRepository repository) {
        this.repository = repository;
    }

    public List<SetlistSummaryDTO> execute() {
        return repository.findAll()
                .stream()
                .map(s -> new SetlistSummaryDTO(s.getId(), s.getName(), s.getItems().size()))
                .toList();
    }
}
