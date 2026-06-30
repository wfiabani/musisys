package br.com.band.band.agenda.infrastructure.web;

import br.com.band.band.agenda.application.dto.AgendaItemDTO;
import br.com.band.band.agenda.application.usecase.GetAgendaRangeUseCase;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final GetAgendaRangeUseCase getAgendaRangeUseCase;

    public AgendaController(GetAgendaRangeUseCase getAgendaRangeUseCase) {
        this.getAgendaRangeUseCase = getAgendaRangeUseCase;
    }

    @GetMapping
    public List<AgendaItemDTO> getByRange(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return getAgendaRangeUseCase.execute(start, end);
    }
}
