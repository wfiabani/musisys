package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.dto.EventDTO;
import br.com.band.band.eventos.application.port.repository.EventRepository;

import java.util.List;

public class ListAllEventsUseCase {

    private final EventRepository eventRepository;

    public ListAllEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> execute(){
        return eventRepository.findAll().stream()
                .map(e -> new EventDTO(e.getId(), e.getType(), e.getDateTime(),
                        e.getLocation(), e.getNotes(), e.getSetlistId()))
                .toList();
    }
}
