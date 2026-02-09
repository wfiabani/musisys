package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.port.DomainEventPublisher;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import br.com.band.band.shared.api.events.SetlistRemovedEvent;

import java.util.UUID;

public class RemoveSetlistUseCase {

    private final SetlistRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RemoveSetlistUseCase(
            SetlistRepository repository,
            DomainEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(UUID setlistId) {
        repository.findById(setlistId)
                .orElseThrow(() -> new RuntimeException("Setlist not found"));

        repository.deleteById(setlistId);

        eventPublisher.publish(
                new SetlistRemovedEvent(setlistId)
        );
    }
}
