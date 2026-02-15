package br.com.band.band.eventos.application;

import br.com.band.band.eventos.application.dto.EventDTO;
import br.com.band.band.eventos.application.usecase.EventWithSetlistOutput;
import br.com.band.band.eventos.application.usecase.GetEventWithSetlistUseCase;
import br.com.band.band.eventos.application.usecase.ListAllEventsUseCase;
import br.com.band.band.eventos.domain.model.Event;

import java.util.List;
import java.util.UUID;

public class EventosService {

    private final ListAllEventsUseCase listAllEventsUseCase;
    private final GetEventWithSetlistUseCase getEventWithSetlistUseCase;

    public EventosService(ListAllEventsUseCase listAllEventsUseCase, GetEventWithSetlistUseCase getEventWithSetlistUseCase) {
        this.listAllEventsUseCase = listAllEventsUseCase;
        this.getEventWithSetlistUseCase = getEventWithSetlistUseCase;
    }

    public List<EventDTO> listAllEvents(){
        return listAllEventsUseCase.execute();
    }

    public EventWithSetlistOutput getById(UUID id){
        return getEventWithSetlistUseCase.execute(id);
    }
}
