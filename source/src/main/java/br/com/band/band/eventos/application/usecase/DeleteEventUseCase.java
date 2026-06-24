package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.exception.EventNotFoundException;
import br.com.band.band.eventos.application.port.repository.EventRepository;

import java.util.UUID;

public class DeleteEventUseCase {

    private final EventRepository repository;

    public DeleteEventUseCase(EventRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        repository.deleteById(id);
    }
}
