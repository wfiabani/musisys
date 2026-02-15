package br.com.band.band.eventos.infrastructure.web;

import br.com.band.band.eventos.application.EventosService;
import br.com.band.band.eventos.application.dto.EventDTO;
import br.com.band.band.eventos.application.usecase.EventWithSetlistOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
public class EventController {

    private final EventosService eventosService;

    public EventController(EventosService eventosService) {
        this.eventosService = eventosService;
    }

    @GetMapping("/{id}")
    public EventWithSetlistOutput getById(@PathVariable UUID id) {
        return eventosService.getById(id);
    }

    @GetMapping
    public List<EventDTO> findAll() {
        return eventosService.listAllEvents();
    }
}
