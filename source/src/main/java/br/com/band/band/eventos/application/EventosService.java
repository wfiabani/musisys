package br.com.band.band.eventos.application;

import br.com.band.band.eventos.application.dto.EventDTO;
import br.com.band.band.eventos.application.dto.SetlistSummaryDto;
import br.com.band.band.eventos.application.usecase.*;
import br.com.band.band.eventos.domain.model.EventType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EventosService {

    private final ListAllEventsUseCase listAllEventsUseCase;
    private final ListAvailableSetlistsUseCase listAvailableSetlistsUseCase;
    private final GetEventWithSetlistUseCase getEventWithSetlistUseCase;
    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;

    public EventosService(
            ListAllEventsUseCase listAllEventsUseCase,
            ListAvailableSetlistsUseCase listAvailableSetlistsUseCase,
            GetEventWithSetlistUseCase getEventWithSetlistUseCase,
            CreateEventUseCase createEventUseCase,
            UpdateEventUseCase updateEventUseCase,
            DeleteEventUseCase deleteEventUseCase
    ) {
        this.listAllEventsUseCase = listAllEventsUseCase;
        this.listAvailableSetlistsUseCase = listAvailableSetlistsUseCase;
        this.getEventWithSetlistUseCase = getEventWithSetlistUseCase;
        this.createEventUseCase = createEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
    }

    public List<EventDTO> listAllEvents() {
        return listAllEventsUseCase.execute();
    }

    public List<SetlistSummaryDto> listAvailableSetlists() {
        return listAvailableSetlistsUseCase.execute();
    }

    public EventWithSetlistOutput getById(UUID id) {
        return getEventWithSetlistUseCase.execute(id);
    }

    public UUID createEvent(EventType type, LocalDateTime dateTime, String location, String notes, UUID setlistId) {
        return createEventUseCase.execute(type, dateTime, location, notes, setlistId);
    }

    public void updateEvent(UUID id, EventType type, LocalDateTime dateTime, String location, String notes, UUID setlistId) {
        updateEventUseCase.execute(id, type, dateTime, location, notes, setlistId);
    }

    public void deleteEvent(UUID id) {
        deleteEventUseCase.execute(id);
    }
}
