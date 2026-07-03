package br.com.band.band.profissionais.application.usecase;

import br.com.band.band.profissionais.application.exception.ProfissionalInUseException;
import br.com.band.band.profissionais.application.exception.ProfissionalNotFoundException;
import br.com.band.band.profissionais.application.port.DomainEventPublisher;
import br.com.band.band.profissionais.application.port.EventosClient;
import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.profissionais.domain.model.Profissional;
import br.com.band.band.shared.api.events.ProfissionalRemovedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProfissionalUseCaseTest {

    @Mock
    private ProfissionalRepository repository;

    @Mock
    private EventosClient eventosClient;

    @Mock
    private DomainEventPublisher eventPublisher;

    @InjectMocks
    private DeleteProfissionalUseCase useCase;

    @Test
    void execute_profissionalNaoExiste_lancaProfissionalNotFoundException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(ProfissionalNotFoundException.class);

        verify(repository, never()).deleteById(any());
        verifyNoInteractions(eventosClient, eventPublisher);
    }

    @Test
    void execute_vinculadoAEventoJaOcorrido_lancaProfissionalInUseException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(profissional(id)));
        when(eventosClient.hasPastEventAssignment(id)).thenReturn(true);

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(ProfissionalInUseException.class);

        verify(repository, never()).deleteById(any());
        verifyNoInteractions(eventPublisher);
    }

    @Test
    void execute_semVinculoComEventoPassado_excluiEPublicaEvento() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(profissional(id)));
        when(eventosClient.hasPastEventAssignment(id)).thenReturn(false);

        useCase.execute(id);

        verify(repository).deleteById(id);
        verify(eventPublisher).publish(new ProfissionalRemovedEvent(id));
    }

    private Profissional profissional(UUID id) {
        return new Profissional(id, "Diego Santos", "Baterista", "Bateria e percussão", true);
    }
}
