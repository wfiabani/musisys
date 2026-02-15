package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.dto.EventDTO;
import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.repertorio.application.dto.SetlistDTO;

import java.util.List;

public class ListAllEventsUseCase {

    private final EventRepository eventRepository;

    public ListAllEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> execute(){
        return eventRepository.findAll().stream().map(item -> {
            return new EventDTO(item.getId(), item.getType(), item.getDateTime(), item.getLocation());
        }).toList();
    }
}
