package br.com.band.band.profissionais.application.usecase;

import br.com.band.band.profissionais.application.exception.ProfissionalInUseException;
import br.com.band.band.profissionais.application.exception.ProfissionalNotFoundException;
import br.com.band.band.profissionais.application.port.DomainEventPublisher;
import br.com.band.band.profissionais.application.port.EventosClient;
import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.shared.api.events.ProfissionalRemovedEvent;

import java.util.UUID;

public class DeleteProfissionalUseCase {

    private final ProfissionalRepository repository;
    private final EventosClient eventosClient;
    private final DomainEventPublisher eventPublisher;

    public DeleteProfissionalUseCase(
            ProfissionalRepository repository,
            EventosClient eventosClient,
            DomainEventPublisher eventPublisher
    ) {
        this.repository = repository;
        this.eventosClient = eventosClient;
        this.eventPublisher = eventPublisher;
    }

    public void execute(UUID id) {
        repository.findById(id).orElseThrow(() -> new ProfissionalNotFoundException(id));

        if (eventosClient.hasPastEventAssignment(id)) {
            throw new ProfissionalInUseException(id);
        }

        repository.deleteById(id);

        eventPublisher.publish(
                new ProfissionalRemovedEvent(id)
        );
    }
}
