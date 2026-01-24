package br.com.band.band.repertorio.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.com.band.band.shared.api.events.MusicAddedToRepertoireEvent;



@Service
public class RepertorioService {

    private final ApplicationEventPublisher publisher;

    public RepertorioService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void addMusic(String musicId) {
        // lógica de domínio omitida
        publisher.publishEvent(new MusicAddedToRepertoireEvent(musicId));
    }
}
