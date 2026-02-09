package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.domain.model.Event;

import java.util.List;
import java.util.UUID;

public class UpdateEventsAfterSetlistRemovalUseCase {

    private final EventRepository eventRepository;

    public UpdateEventsAfterSetlistRemovalUseCase(
            EventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }

    public void execute(UUID setlistId) {

        List<Event> events = eventRepository.findBySetlistId(setlistId);

        events.forEach(event -> event.removeSetlist());

        eventRepository.saveAll(events);
    }
}
