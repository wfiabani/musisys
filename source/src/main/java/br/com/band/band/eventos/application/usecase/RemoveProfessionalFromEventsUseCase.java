package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.domain.model.Event;

import java.util.List;
import java.util.UUID;

public class RemoveProfessionalFromEventsUseCase {

    private final EventRepository eventRepository;

    public RemoveProfessionalFromEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void execute(UUID professionalId) {

        List<Event> events = eventRepository.findByProfessionalId(professionalId);

        events.forEach(event -> {
            List<UUID> remaining = event.getProfessionalIds().stream()
                    .filter(id -> !id.equals(professionalId))
                    .toList();
            event.assignProfessionals(remaining);
        });

        eventRepository.saveAll(events);
    }
}
