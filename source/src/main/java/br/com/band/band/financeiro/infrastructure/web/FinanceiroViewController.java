package br.com.band.band.financeiro.infrastructure.web;

import br.com.band.band.financeiro.application.FinanceiroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/financeiro/ui")
public class FinanceiroViewController {

    private final FinanceiroService service;

    public FinanceiroViewController(FinanceiroService service) {
        this.service = service;
    }

    @GetMapping
    public String dashboard(
            Model model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        LocalDate today = LocalDate.now();
        int y = year  != null ? year  : today.getYear();
        int m = month != null ? month : today.getMonthValue();

        model.addAttribute("summary",      service.getMonthlySummary(y, m));
        model.addAttribute("transactions", service.listByMonth(y, m));
        model.addAttribute("projections",  service.getMonthlyProjections(6));
        model.addAttribute("currentYear",  y);
        model.addAttribute("currentMonth", m);
        model.addAttribute("pageTitle",    "Financeiro");
        return "financeiro/dashboard";
    }
}
