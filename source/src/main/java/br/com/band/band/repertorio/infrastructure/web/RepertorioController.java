package br.com.band.band.repertorio.infrastructure.web;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.dto.SetlistDTO;
import br.com.band.band.repertorio.domain.model.Music;
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

    @GetMapping("/musics")
    public List<Music> listAll() {
        return repertorioService.listAllMusics();
    }

    @GetMapping("/setlist/{setlistId}")
    public SetlistDTO getSetlistWithMusics(@PathVariable UUID setlistId) {
        return repertorioService.getSetlistWithMusics(setlistId);
    }

    @DeleteMapping("/setlists/{setlistId}")
    public void removeSetlist(@PathVariable UUID setlistId) {
        repertorioService.removeSetlist(setlistId);
    }

}
