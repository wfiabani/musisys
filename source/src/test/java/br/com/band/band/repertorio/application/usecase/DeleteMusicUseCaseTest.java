package br.com.band.band.repertorio.application.usecase;

import br.com.band.band.repertorio.application.exception.MusicInUseException;
import br.com.band.band.repertorio.application.exception.MusicNotFoundException;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import br.com.band.band.repertorio.domain.model.Music;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteMusicUseCaseTest {

    @Mock
    private MusicRepository repository;

    @InjectMocks
    private DeleteMusicUseCase useCase;

    @Test
    void execute_musicaNaoExiste_lancaMusicNotFoundException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(MusicNotFoundException.class);

        verify(repository, never()).deleteById(any());
    }

    @Test
    void execute_musicaPertenceASetlist_lancaMusicInUseException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(musicaDeTeste(id)));
        when(repository.existsInAnySetlist(id)).thenReturn(true);

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(MusicInUseException.class);

        verify(repository, never()).deleteById(any());
    }

    @Test
    void execute_musicaLivreParaExclusao_chamaDeleteById() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(musicaDeTeste(id)));
        when(repository.existsInAnySetlist(id)).thenReturn(false);

        useCase.execute(id);

        verify(repository).deleteById(id);
    }

    @Test
    void execute_musicaLivreParaExclusao_naoVerificaSetlistSeNaoExistir() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id))
                .isInstanceOf(MusicNotFoundException.class);

        verify(repository, never()).existsInAnySetlist(any());
    }

    private Music musicaDeTeste(UUID id) {
        return new Music(id, "Title", "C", "Author");
    }
}
