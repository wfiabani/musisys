package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.exception.EventNotFoundException;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.domain.model.EventType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UpdateEventUseCase {

    private final EventRepository repository;

    public UpdateEventUseCase(EventRepository repository) {
        this.repository = repository;
    }

    public void execute(
            UUID id,
            EventType type,
            LocalDateTime dateTime,
            String location,
            String notes,
            UUID setlistId,
            List<UUID> professionalIds
    ) {
        var event = repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        event.changeType(type);
        event.changeDateTime(dateTime);
        event.changeLocation(location);
        event.updateNotes(notes);
        if (setlistId != null) event.attachSetlist(setlistId);
        else event.removeSetlist();
        event.assignProfessionals(professionalIds != null ? professionalIds : List.of());
        repository.save(event);
    }
}
