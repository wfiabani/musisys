package br.com.band.band.repertorio.infrastructure.web;

import br.com.band.band.repertorio.application.RepertorioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/repertorio/ui")
public class RepertorioViewController {

    private final RepertorioService repertorioService;

    public RepertorioViewController(RepertorioService repertorioService) {
        this.repertorioService = repertorioService;
    }

    @GetMapping("/musics")
    public String musicsPage(Model model) {
        model.addAttribute("pageTitle", "Músicas");
        model.addAttribute("musics", repertorioService.listAllMusics());
        return "repertorio/musics";
    }

    @GetMapping("/setlists")
    public String setlistsPage(Model model) {
        model.addAttribute("pageTitle", "Setlists");
        model.addAttribute("setlists", repertorioService.listAllSetlists());
        return "repertorio/setlists";
    }

    @GetMapping("/setlists/{id}")
    public String setlistEditorPage(@PathVariable UUID id, Model model) {
        var setlist = repertorioService.getSetlistWithMusics(id);
        var allMusics = repertorioService.listAllMusics();

        model.addAttribute("pageTitle", setlist.name());
        model.addAttribute("setlist", setlist);
        model.addAttribute("allMusics", allMusics);
        return "repertorio/setlist-editor";
    }
}
