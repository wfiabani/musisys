package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.domain.model.EventType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateEventsAfterSetlistRemovalUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private UpdateEventsAfterSetlistRemovalUseCase useCase;

    @Test
    void execute_nenhumEventoReferenciandoSetlist_chamaSaveAllComListaVazia() {
        UUID setlistId = UUID.randomUUID();
        when(eventRepository.findBySetlistId(setlistId)).thenReturn(List.of());

        useCase.execute(setlistId);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Event>> captor = ArgumentCaptor.forClass(List.class);
        verify(eventRepository).saveAll(captor.capture());
        assertThat(captor.getValue()).isEmpty();
    }

    @Test
    void execute_eventosExistem_zeraSetlistIdEmTodosEChaSaveAll() {
        UUID setlistId = UUID.randomUUID();
        Event evento1 = eventoComSetlist(setlistId);
        Event evento2 = eventoComSetlist(setlistId);
        Event evento3 = eventoComSetlist(setlistId);

        when(eventRepository.findBySetlistId(setlistId)).thenReturn(List.of(evento1, evento2, evento3));

        useCase.execute(setlistId);

        @SuppressWarnings("unchecked")
        ArgumentCaptor<List<Event>> captor = ArgumentCaptor.forClass(List.class);
        verify(eventRepository).saveAll(captor.capture());

        List<Event> salvos = captor.getValue();
        assertThat(salvos).hasSize(3);
        assertThat(salvos).allSatisfy(e -> assertThat(e.getSetlistId()).isNull());
    }

    @Test
    void execute_naoAlteraEventosSemSetlist() {
        UUID setlistId = UUID.randomUUID();
        when(eventRepository.findBySetlistId(setlistId)).thenReturn(List.of());

        useCase.execute(setlistId);

        verify(eventRepository, never()).findAll();
    }

    private Event eventoComSetlist(UUID setlistId) {
        Event event = new Event(
                UUID.randomUUID(),
                EventType.SHOW,
                LocalDateTime.now(),
                "Location",
                null
        );
        event.attachSetlist(setlistId);
        return event;
    }
}
