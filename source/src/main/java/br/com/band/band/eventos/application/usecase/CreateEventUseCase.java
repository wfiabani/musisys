package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.domain.model.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateEventUseCase {

    private final EventRepository repository;

    public CreateEventUseCase(EventRepository repository) {
        this.repository = repository;
    }

    public UUID execute(EventType type, LocalDateTime dateTime, String location, String notes, UUID setlistId) {
        Event event = new Event(UUID.randomUUID(), type, dateTime, location, notes);
        if (setlistId != null) event.attachSetlist(setlistId);
        repository.save(event);
        return event.getId();
    }
}
