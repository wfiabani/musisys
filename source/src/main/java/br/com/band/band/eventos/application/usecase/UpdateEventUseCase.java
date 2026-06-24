package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.exception.EventNotFoundException;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.domain.model.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateEventUseCase {

    private final EventRepository repository;

    public UpdateEventUseCase(EventRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, EventType type, LocalDateTime dateTime, String location, String notes, UUID setlistId) {
        var event = repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        event.changeType(type);
        event.changeDateTime(dateTime);
        event.changeLocation(location);
        event.updateNotes(notes);
        if (setlistId != null) event.attachSetlist(setlistId);
        else event.removeSetlist();
        repository.save(event);
    }
}
