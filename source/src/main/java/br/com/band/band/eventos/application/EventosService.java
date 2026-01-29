package br.com.band.band.eventos.application;

import br.com.band.band.eventos.application.usecase.ListAllEventsUseCase;
import br.com.band.band.eventos.domain.model.Event;

import java.util.List;

public class EventosService {

    private final ListAllEventsUseCase listAllEventsUseCase;

    public EventosService(ListAllEventsUseCase listAllEventsUseCase) {
        this.listAllEventsUseCase = listAllEventsUseCase;
    }

    public List<Event> listAllEvents(){
        return listAllEventsUseCase.execute();
    }
}
