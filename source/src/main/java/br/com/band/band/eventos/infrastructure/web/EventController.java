package br.com.band.band.eventos.infrastructure.web;

import br.com.band.band.eventos.application.EventosService;
import br.com.band.band.eventos.application.dto.EventDTO;
import br.com.band.band.eventos.application.usecase.EventWithSetlistOutput;
import br.com.band.band.eventos.domain.model.EventType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
public class EventController {

    private final EventosService eventosService;

    private static final DateTimeFormatter DT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public EventController(EventosService eventosService) {
        this.eventosService = eventosService;
    }

    @GetMapping
    public List<EventDTO> findAll() {
        return eventosService.listAllEvents();
    }

    @GetMapping("/{id}")
    public EventWithSetlistOutput getById(@PathVariable UUID id) {
        return eventosService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createEvent(@RequestBody EventRequest request) {
        return eventosService.createEvent(
                EventType.valueOf(request.type()),
                LocalDateTime.parse(request.dateTime(), DT_FORMATTER),
                request.location(),
                request.notes(),
                request.setlistId(),
                request.professionalIds()
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable UUID id, @RequestBody EventRequest request) {
        eventosService.updateEvent(
                id,
                EventType.valueOf(request.type()),
                LocalDateTime.parse(request.dateTime(), DT_FORMATTER),
                request.location(),
                request.notes(),
                request.setlistId(),
                request.professionalIds()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable UUID id) {
        eventosService.deleteEvent(id);
    }

    record EventRequest(
            String type,
            String dateTime,
            String location,
            String notes,
            UUID setlistId,
            List<UUID> professionalIds
    ) {}
}
