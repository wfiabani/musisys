package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.domain.repository.EventRepository;

import java.util.List;

public class ListAllEventsUseCase {

    private final EventRepository eventRepository;

    public ListAllEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> execute(){
        return eventRepository.findAll();
    }
}
