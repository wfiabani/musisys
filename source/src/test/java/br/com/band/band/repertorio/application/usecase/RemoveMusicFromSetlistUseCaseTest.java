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
class RemoveMusicFromSetlistUseCaseTest {

    @Mock
    private SetlistRepository setlistRepository;

    @InjectMocks
    private RemoveMusicFromSetlistUseCase useCase;

    @Test
    void execute_setlistNaoExiste_lancaSetlistNotFoundException() {
        UUID setlistId = UUID.randomUUID();
        when(setlistRepository.findById(setlistId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(setlistId, UUID.randomUUID()))
                .isInstanceOf(SetlistNotFoundException.class);

        verify(setlistRepository, never()).save(any());
    }

    @Test
    void execute_musicaPresente_salvaSemMusicaEComPosicoesFechadas() {
        UUID setlistId = UUID.randomUUID();
        UUID musicA = UUID.randomUUID();
        UUID musicB = UUID.randomUUID();
        UUID musicC = UUID.randomUUID();

        Setlist setlist = new Setlist(setlistId, "Test");
        setlist.addMusic(musicA);
        setlist.addMusic(musicB);
        setlist.addMusic(musicC);

        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        useCase.execute(setlistId, musicB);

        ArgumentCaptor<Setlist> captor = ArgumentCaptor.forClass(Setlist.class);
        verify(setlistRepository).save(captor.capture());
        Setlist saved = captor.getValue();

        assertThat(saved.getItems()).hasSize(2);
        assertThat(saved.getItems().get(0).getMusicId()).isEqualTo(musicA);
        assertThat(saved.getItems().get(0).getPosition()).isEqualTo(1);
        assertThat(saved.getItems().get(1).getMusicId()).isEqualTo(musicC);
        assertThat(saved.getItems().get(1).getPosition()).isEqualTo(2);
    }

    @Test
    void execute_remocaoDoPrimeiroItem_posicoesContinuamNormalizadas() {
        UUID setlistId = UUID.randomUUID();
        UUID musicA = UUID.randomUUID();
        UUID musicB = UUID.randomUUID();

        Setlist setlist = new Setlist(setlistId, "Test");
        setlist.addMusic(musicA);
        setlist.addMusic(musicB);

        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        useCase.execute(setlistId, musicA);

        ArgumentCaptor<Setlist> captor = ArgumentCaptor.forClass(Setlist.class);
        verify(setlistRepository).save(captor.capture());
        Setlist saved = captor.getValue();

        assertThat(saved.getItems()).hasSize(1);
        assertThat(saved.getItems().get(0).getMusicId()).isEqualTo(musicB);
        assertThat(saved.getItems().get(0).getPosition()).isEqualTo(1);
    }
}
