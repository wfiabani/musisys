package br.com.band.band.eventos.infrastructure.listener;

import br.com.band.band.shared.api.events.SetlistRemovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SetlistRemovedEventListener {

    @EventListener
    public void on(SetlistRemovedEvent event) {
        System.out.println(
                "ok, seu charopão, eu entendi e recebi o event -> setlistId = "
                        + event.setlistId()
        );
    }
}
