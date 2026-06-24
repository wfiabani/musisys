package br.com.band.band.repertorio.infrastructure.web;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.dto.SetlistDTO;
import br.com.band.band.repertorio.application.dto.SetlistSummaryDTO;
import br.com.band.band.repertorio.domain.model.Music;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/repertorio")
public class RepertorioController {

    private final RepertorioService repertorioService;

    public RepertorioController(RepertorioService repertorioService) {
        this.repertorioService = repertorioService;
    }

    // ── Músicas ────────────────────────────────────────────────────────────────

    @GetMapping("/musics")
    public List<Music> listMusics() {
        return repertorioService.listAllMusics();
    }

    @PostMapping("/musics")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createMusic(@RequestBody CreateMusicRequest request) {
        return repertorioService.createMusic(request.title(), request.author(), request.key());
    }

    @PutMapping("/musics/{musicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMusic(@PathVariable UUID musicId, @RequestBody UpdateMusicRequest request) {
        repertorioService.updateMusic(musicId, request.title(), request.author(), request.key());
    }

    @DeleteMapping("/musics/{musicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMusic(@PathVariable UUID musicId) {
        repertorioService.deleteMusic(musicId);
    }

    // ── Setlists ───────────────────────────────────────────────────────────────

    @GetMapping("/setlists")
    public List<SetlistSummaryDTO> listSetlists() {
        return repertorioService.listAllSetlists();
    }

    @PostMapping("/setlists")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createSetlist(@RequestBody CreateSetlistRequest request) {
        return repertorioService.createSetlist(request.name());
    }

    @GetMapping("/setlists/{setlistId}")
    public SetlistDTO getSetlist(@PathVariable UUID setlistId) {
        return repertorioService.getSetlistWithMusics(setlistId);
    }

    @DeleteMapping("/setlists/{setlistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSetlist(@PathVariable UUID setlistId) {
        repertorioService.removeSetlist(setlistId);
    }

    // ── Itens do Setlist ───────────────────────────────────────────────────────

    @PostMapping("/setlists/{setlistId}/musics/{musicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMusicToSetlist(@PathVariable UUID setlistId, @PathVariable UUID musicId) {
        repertorioService.addMusicToSetlist(setlistId, musicId);
    }

    @DeleteMapping("/setlists/{setlistId}/musics/{musicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMusicFromSetlist(@PathVariable UUID setlistId, @PathVariable UUID musicId) {
        repertorioService.removeMusicFromSetlist(setlistId, musicId);
    }

    @PutMapping("/setlists/{setlistId}/musics/{musicId}/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reorderMusic(
            @PathVariable UUID setlistId,
            @PathVariable UUID musicId,
            @RequestBody ReorderRequest request
    ) {
        repertorioService.reorderSetlist(setlistId, musicId, request.position());
    }

    // ── Request records ────────────────────────────────────────────────────────

    record CreateMusicRequest(String title, String author, String key) {}

    record UpdateMusicRequest(String title, String author, String key) {}

    record CreateSetlistRequest(String name) {}

    record ReorderRequest(int position) {}
}
