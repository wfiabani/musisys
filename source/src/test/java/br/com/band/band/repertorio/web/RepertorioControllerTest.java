package br.com.band.band.repertorio.web;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.exception.MusicInUseException;
import br.com.band.band.repertorio.application.exception.MusicNotFoundException;
import br.com.band.band.repertorio.application.exception.SetlistNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testa os status HTTP do RepertorioController.
 * Usa @SpringBootTest + @MockBean para evitar conflitos de contexto com RepertorioConfig.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = "app.repertorio.base-url=http://localhost"
)
@AutoConfigureMockMvc
class RepertorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepertorioService repertorioService;

    // ── DELETE /repertorio/musics/{id} ────────────────────────────────────────

    @Test
    void deleteMusic_musicaNaoEncontrada_retorna404() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(new MusicNotFoundException(id)).when(repertorioService).deleteMusic(id);

        mockMvc.perform(delete("/repertorio/musics/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMusic_musicaEmUso_retorna409() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(new MusicInUseException(id)).when(repertorioService).deleteMusic(id);

        mockMvc.perform(delete("/repertorio/musics/{id}", id))
                .andExpect(status().isConflict());
    }

    @Test
    void deleteMusic_sucesso_retorna204() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/repertorio/musics/{id}", id))
                .andExpect(status().isNoContent());
    }

    // ── DELETE /repertorio/setlists/{id} ─────────────────────────────────────

    @Test
    void deleteSetlist_setlistNaoEncontrado_retorna404() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(new SetlistNotFoundException(id)).when(repertorioService).removeSetlist(id);

        mockMvc.perform(delete("/repertorio/setlists/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteSetlist_sucesso_retorna204() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/repertorio/setlists/{id}", id))
                .andExpect(status().isNoContent());
    }

    // ── PUT /repertorio/setlists/{id}/musics/{mid}/position ──────────────────

    @Test
    void reorderMusicaDoSetlist_setlistNaoEncontrado_retorna404() throws Exception {
        UUID setlistId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        doThrow(new SetlistNotFoundException(setlistId))
                .when(repertorioService).reorderSetlist(setlistId, musicId, 2);

        mockMvc.perform(put("/repertorio/setlists/{setlistId}/musics/{musicId}/position",
                        setlistId, musicId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"position\": 2}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void reorderMusicaDoSetlist_sucesso_retorna204() throws Exception {
        UUID setlistId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();

        mockMvc.perform(put("/repertorio/setlists/{setlistId}/musics/{musicId}/position",
                        setlistId, musicId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"position\": 2}"))
                .andExpect(status().isNoContent());
    }
}
