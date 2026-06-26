package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.exception.SetlistNotFoundException;
import br.com.band.band.repertorio.application.port.DomainEventPublisher;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.shared.api.events.SetlistRemovedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveSetlistUseCaseTest {

    @Mock
    private SetlistRepository repository;

    @Mock
    private DomainEventPublisher eventPublisher;

    @InjectMocks
    private RemoveSetlistUseCase useCase;

    @Test
    void execute_setlistNaoExiste_lancaSetlistNotFoundExceptionEEventoNaoPublicado() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(SetlistNotFoundException.class);

        verify(eventPublisher, never()).publish(any());
    }

    @Test
    void execute_setlistExiste_deletaEPublicaEventoComIdCorreto() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(new Setlist(id, "Test")));

        useCase.execute(id);

        verify(repository).deleteById(id);

        ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
        verify(eventPublisher).publish(captor.capture());
        assertThat(captor.getValue())
                .isInstanceOf(SetlistRemovedEvent.class)
                .extracting(e -> ((SetlistRemovedEvent) e).setlistId())
                .isEqualTo(id);
    }

    @Test
    void execute_setlistExiste_deletaAntesDePublicarEvento() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(new Setlist(id, "Test")));

        var inOrder = inOrder(repository, eventPublisher);

        useCase.execute(id);

        inOrder.verify(repository).deleteById(id);
        inOrder.verify(eventPublisher).publish(any());
    }
}
