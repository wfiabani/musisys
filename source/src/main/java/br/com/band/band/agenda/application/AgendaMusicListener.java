package br.com.band.band.agenda.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.band.band.shared.api.events.MusicAddedToRepertoireEvent;

@Component
public class AgendaMusicListener {

    @EventListener
    public void on(MusicAddedToRepertoireEvent event) {
        System.out.println("Agenda reagiu à música: " + event.musicId());
    }
}
