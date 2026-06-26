package br.com.band.band.repertorio.domain;

import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.domain.model.SetlistItem;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class SetlistTest {

    private static final UUID MUSIC_A = UUID.randomUUID();
    private static final UUID MUSIC_B = UUID.randomUUID();
    private static final UUID MUSIC_C = UUID.randomUUID();

    private Setlist setlistWith(UUID... musicIds) {
        Setlist setlist = new Setlist(UUID.randomUUID(), "Test");
        for (UUID id : musicIds) {
            setlist.addMusic(id);
        }
        return setlist;
    }

    @Test
    void addMusic_primeiraMusica_recebePosicaoUm() {
        Setlist setlist = new Setlist(UUID.randomUUID(), "Test");
        setlist.addMusic(MUSIC_A);

        assertThat(setlist.getItems().get(0).getPosition()).isEqualTo(1);
    }

    @Test
    void addMusic_terceiraMusicaRecebePositionTres() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B, MUSIC_C);

        assertThat(setlist.getItems().get(2).getPosition()).isEqualTo(3);
    }

    @Test
    void removeMusic_itemDoMeio_fechaGapNasPosicoes() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B, MUSIC_C);
        setlist.removeMusic(MUSIC_B);

        List<SetlistItem> items = setlist.getItems();
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getMusicId()).isEqualTo(MUSIC_A);
        assertThat(items.get(0).getPosition()).isEqualTo(1);
        assertThat(items.get(1).getMusicId()).isEqualTo(MUSIC_C);
        assertThat(items.get(1).getPosition()).isEqualTo(2);
    }

    @Test
    void removeMusic_idInexistente_naoAlteraLista() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B);

        setlist.removeMusic(UUID.randomUUID());

        assertThat(setlist.getItems()).hasSize(2);
    }

    @Test
    void moveMusic_paraAPrimeiraPosicao_reordenaCorretamente() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B, MUSIC_C);

        setlist.moveMusic(MUSIC_C, 1);

        List<SetlistItem> items = setlist.getItems();
        assertThat(items.get(0).getMusicId()).isEqualTo(MUSIC_C);
        assertThat(items.get(1).getMusicId()).isEqualTo(MUSIC_A);
        assertThat(items.get(2).getMusicId()).isEqualTo(MUSIC_B);
        assertPosicoesNormalizadas(items);
    }

    @Test
    void moveMusic_paraAUltimaPosicao_reordenaCorretamente() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B, MUSIC_C);

        setlist.moveMusic(MUSIC_A, 3);

        List<SetlistItem> items = setlist.getItems();
        assertThat(items.get(0).getMusicId()).isEqualTo(MUSIC_B);
        assertThat(items.get(1).getMusicId()).isEqualTo(MUSIC_C);
        assertThat(items.get(2).getMusicId()).isEqualTo(MUSIC_A);
        assertPosicoesNormalizadas(items);
    }

    @Test
    void moveMusic_posicaoZero_lancaIllegalArgumentException() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B);

        assertThatThrownBy(() -> setlist.moveMusic(MUSIC_A, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid position");
    }

    @Test
    void moveMusic_posicaoAcimaDoTamanho_lancaIllegalArgumentException() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B);

        assertThatThrownBy(() -> setlist.moveMusic(MUSIC_A, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid position");
    }

    @Test
    void moveMusic_musicaAusenteNoSetlist_lancaNoSuchElementException() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B);

        assertThatThrownBy(() -> setlist.moveMusic(MUSIC_C, 1))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void getItems_retornaListaImutavel() {
        Setlist setlist = setlistWith(MUSIC_A);

        List<SetlistItem> items = setlist.getItems();

        assertThatThrownBy(items::clear)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void normalizePositions_aposMultiplasMutacoes_posicoesSaoSequenciaisEContiguas() {
        Setlist setlist = setlistWith(MUSIC_A, MUSIC_B, MUSIC_C);
        setlist.removeMusic(MUSIC_A);
        setlist.addMusic(MUSIC_A);
        setlist.moveMusic(MUSIC_B, 2);

        assertPosicoesNormalizadas(setlist.getItems());
    }

    private void assertPosicoesNormalizadas(List<SetlistItem> items) {
        for (int i = 0; i < items.size(); i++) {
            assertThat(items.get(i).getPosition())
                    .as("posição do item %d deve ser %d", i, i + 1)
                    .isEqualTo(i + 1);
        }
    }
}
