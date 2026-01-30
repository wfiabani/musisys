package br.com.band.band.eventos.infrastructure.web;

import br.com.band.band.eventos.application.usecase.GetEventWithSetlistUseCase;
import br.com.band.band.eventos.application.usecase.EventWithSetlistOutput;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventos")
public class EventController {

    private final GetEventWithSetlistUseCase useCase;

    public EventController(GetEventWithSetlistUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{id}")
    public EventWithSetlistOutput getById(@PathVariable UUID id) {
        return useCase.execute(id);
    }
}
