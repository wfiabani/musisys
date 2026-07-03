package br.com.band.band.eventos.infrastructure.listener;

import br.com.band.band.eventos.application.usecase.RemoveProfessionalFromEventsUseCase;
import br.com.band.band.shared.api.events.ProfissionalRemovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalRemovedEventListener {

    private final RemoveProfessionalFromEventsUseCase removeProfessionalFromEventsUseCase;

    public ProfissionalRemovedEventListener(RemoveProfessionalFromEventsUseCase removeProfessionalFromEventsUseCase) {
        this.removeProfessionalFromEventsUseCase = removeProfessionalFromEventsUseCase;
    }

    @EventListener
    public void on(ProfissionalRemovedEvent event) {
        removeProfessionalFromEventsUseCase.execute(event.professionalId());
    }
}
