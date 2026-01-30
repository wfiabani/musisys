package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.dto.SetlistDto;
import br.com.band.band.eventos.application.port.SetlistClient;
import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.domain.repository.EventRepository;

import java.util.Optional;
import java.util.UUID;

public class GetEventWithSetlistUseCase {

    private final EventRepository eventRepository;
    private final SetlistClient setlistClient;

    public GetEventWithSetlistUseCase(
            EventRepository eventRepository,
            SetlistClient setlistClient
    ) {
        this.eventRepository = eventRepository;
        this.setlistClient = setlistClient;
    }

    public EventWithSetlistOutput execute(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Optional<SetlistDto> setlist =
                Optional.ofNullable(event.getSetlistId())
                        .flatMap(setlistClient::findById);

        return EventWithSetlistOutput.from(event, setlist.orElse(null));
    }
}
