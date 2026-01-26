package br.com.band.band.repertorio.interfaceadapter.web;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.domain.music.model.Music;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repertorio/musics")
public class MusicController {

    private final RepertorioService repertorioService;

    public MusicController(RepertorioService repertorioService) {
        this.repertorioService = repertorioService;
    }

    @GetMapping
    public List<Music> listAll() {
        return repertorioService.listAllMusics();
    }
}
