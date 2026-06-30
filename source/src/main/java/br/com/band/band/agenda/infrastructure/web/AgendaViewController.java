package br.com.band.band.agenda.infrastructure.web;

import br.com.band.band.agenda.application.usecase.GetAgendaRangeUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/agenda/ui")
public class AgendaViewController {

    private final GetAgendaRangeUseCase getAgendaRangeUseCase;

    public AgendaViewController(GetAgendaRangeUseCase getAgendaRangeUseCase) {
        this.getAgendaRangeUseCase = getAgendaRangeUseCase;
    }

    @GetMapping
    public String agendaPage(Model model) {
        LocalDate start = LocalDate.now();
        LocalDate end   = start.plusDays(5);

        model.addAttribute("items",     getAgendaRangeUseCase.execute(start, end));
        model.addAttribute("startDate", start.toString());
        model.addAttribute("endDate",   end.toString());
        model.addAttribute("pageTitle", "Agenda");
        return "agenda/agenda";
    }
}
