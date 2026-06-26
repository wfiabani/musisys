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
class AddMusicToSetlistUseCaseTest {

    @Mock
    private SetlistRepository setlistRepository;

    @InjectMocks
    private AddMusicToSetlistUseCase useCase;

    @Test
    void execute_setlistNaoExiste_lancaSetlistNotFoundException() {
        UUID setlistId = UUID.randomUUID();
        when(setlistRepository.findById(setlistId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(setlistId, UUID.randomUUID()))
                .isInstanceOf(SetlistNotFoundException.class);

        verify(setlistRepository, never()).save(any());
    }

    @Test
    void execute_setlistVazia_adicionaMusicaNaPrimeiraPosicao() {
        UUID setlistId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();

        Setlist setlist = new Setlist(setlistId, "Test");
        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        useCase.execute(setlistId, musicId);

        ArgumentCaptor<Setlist> captor = ArgumentCaptor.forClass(Setlist.class);
        verify(setlistRepository).save(captor.capture());
        Setlist saved = captor.getValue();

        assertThat(saved.getItems()).hasSize(1);
        assertThat(saved.getItems().get(0).getMusicId()).isEqualTo(musicId);
        assertThat(saved.getItems().get(0).getPosition()).isEqualTo(1);
    }

    @Test
    void execute_setlistComItens_adicionaMusicaNoFinalComPosicaoCorreta() {
        UUID setlistId = UUID.randomUUID();
        UUID musicaExistente = UUID.randomUUID();
        UUID novaMusica = UUID.randomUUID();

        Setlist setlist = new Setlist(setlistId, "Test");
        setlist.addMusic(musicaExistente);

        when(setlistRepository.findById(setlistId)).thenReturn(Optional.of(setlist));

        useCase.execute(setlistId, novaMusica);

        ArgumentCaptor<Setlist> captor = ArgumentCaptor.forClass(Setlist.class);
        verify(setlistRepository).save(captor.capture());
        Setlist saved = captor.getValue();

        assertThat(saved.getItems()).hasSize(2);
        assertThat(saved.getItems().get(1).getMusicId()).isEqualTo(novaMusica);
        assertThat(saved.getItems().get(1).getPosition()).isEqualTo(2);
    }
}
