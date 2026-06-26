package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.exception.SetlistNotFoundException;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import br.com.band.band.repertorio.domain.model.Setlist;
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
class ReorderSetlistUseCaseTest {

    @Mock
    private SetlistRepository setlistRepository;

    @InjectMocks
    private ReorderSetlistUseCase useCase;

    @Test
    void execute_setlistNaoExiste_lancaSetlistNotFoundException() {
        UUID setlistId = UUID.randomUUID();
        when(setlistRepository.findById(setlistId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(setlistId, UUID.randomUUID(), 1))
                .isInstanceOf(SetlistNotFoundException.class);

        verify(setlistRepository, never()).save(any());
    }

    @Test
    void execute_posicaoInvalida_propagaIllegalArgumentException() {
        UUID setlistId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        Setlist setlist = new Setlist(setlistId, "Test");
        setlist.addMusic(musicId);
        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        assertThatThrownBy(() -> useCase.execute(setlistId, musicId, 0))
                .isInstanceOf(IllegalArgumentException.class);

        verify(setlistRepository, never()).save(any());
    }

    @Test
    void execute_posicaoValida_salvaSetlistComOrdemAtualizada() {
        UUID setlistId = UUID.randomUUID();
        UUID musicA = UUID.randomUUID();
        UUID musicB = UUID.randomUUID();
        UUID musicC = UUID.randomUUID();

        Setlist setlist = new Setlist(setlistId, "Test");
        setlist.addMusic(musicA);
        setlist.addMusic(musicB);
        setlist.addMusic(musicC);

        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        useCase.execute(setlistId, musicC, 1);

        ArgumentCaptor<Setlist> captor = ArgumentCaptor.forClass(Setlist.class);
        verify(setlistRepository).save(captor.capture());
        Setlist saved = captor.getValue();
        assertThat(saved.getItems().get(0).getMusicId()).isEqualTo(musicC);
        assertThat(saved.getItems().get(1).getMusicId()).isEqualTo(musicA);
        assertThat(saved.getItems().get(2).getMusicId()).isEqualTo(musicB);
    }

    @Test
    void execute_posicaoValidaParaUltima_salvaSetlistComMusicaNaUltimaPosicao() {
        UUID setlistId = UUID.randomUUID();
        UUID musicA = UUID.randomUUID();
        UUID musicB = UUID.randomUUID();
        UUID musicC = UUID.randomUUID();

        Setlist setlist = new Setlist(setlistId, "Test");
        setlist.addMusic(musicA);
        setlist.addMusic(musicB);
        setlist.addMusic(musicC);

        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        useCase.execute(setlistId, musicA, 3);

        ArgumentCaptor<Setlist> captor = ArgumentCaptor.forClass(Setlist.class);
        verify(setlistRepository).save(captor.capture());
        Setlist saved = captor.getValue();
        assertThat(saved.getItems().get(2).getMusicId()).isEqualTo(musicA);
        assertThat(saved.getItems().get(2).getPosition()).isEqualTo(3);
    }
}
