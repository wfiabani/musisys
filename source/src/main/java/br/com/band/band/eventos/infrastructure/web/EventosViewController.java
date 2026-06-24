package br.com.band.band.eventos.infrastructure.web;

import br.com.band.band.eventos.application.EventosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/eventos/ui")
public class EventosViewController {

    private final EventosService eventosService;

    public EventosViewController(EventosService eventosService) {
        this.eventosService = eventosService;
    }

    @GetMapping
    public String eventsPage(Model model) {
        model.addAttribute("pageTitle", "Eventos");
        model.addAttribute("events", eventosService.listAllEvents());
        return "eventos/events";
    }

    @GetMapping("/{id}")
    public String eventDetailPage(@PathVariable UUID id, Model model) {
        var event = eventosService.getById(id);
        model.addAttribute("pageTitle", event.location());
        model.addAttribute("event", event);
        return "eventos/event-detail";
    }
}
