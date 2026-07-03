package br.com.band.band.profissionais.infrastructure.web;

import br.com.band.band.profissionais.application.ProfissionaisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profissionais/ui")
public class ProfissionaisViewController {

    private final ProfissionaisService profissionaisService;

    public ProfissionaisViewController(ProfissionaisService profissionaisService) {
        this.profissionaisService = profissionaisService;
    }

    @GetMapping
    public String professionalsPage(Model model) {
        model.addAttribute("pageTitle", "Profissionais");
        model.addAttribute("professionals", profissionaisService.listAllProfissionais());
        return "profissionais/professionals";
    }
}
