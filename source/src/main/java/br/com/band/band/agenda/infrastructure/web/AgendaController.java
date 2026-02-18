package br.com.band.band.agenda.infrastructure.web;

import br.com.band.band.agenda.application.AgendaService;
import br.com.band.band.agenda.domain.model.AgendaItem;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<AgendaItem> getByDate(@RequestParam LocalDate date) {
        return agendaService.getAgendaByDate(date);
    }
}
