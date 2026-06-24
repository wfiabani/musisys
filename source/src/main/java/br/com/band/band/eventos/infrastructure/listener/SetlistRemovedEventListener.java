package br.com.band.band.eventos.infrastructure.listener;

import br.com.band.band.eventos.application.usecase.UpdateEventsAfterSetlistRemovalUseCase;
import br.com.band.band.shared.api.events.SetlistRemovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SetlistRemovedEventListener {

    private final UpdateEventsAfterSetlistRemovalUseCase updateEventsAfterSetlistRemovalUseCase;

    public SetlistRemovedEventListener(UpdateEventsAfterSetlistRemovalUseCase updateEventsAfterSetlistRemovalUseCase) {
        this.updateEventsAfterSetlistRemovalUseCase = updateEventsAfterSetlistRemovalUseCase;
    }

    @EventListener
    public void on(SetlistRemovedEvent event) {
        updateEventsAfterSetlistRemovalUseCase.execute(event.setlistId());
    }
}
