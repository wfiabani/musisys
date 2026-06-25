package br.com.band.band.financeiro.infrastructure.web;

import br.com.band.band.financeiro.application.FinanceiroService;
import br.com.band.band.financeiro.application.dto.MonthlyProjectionDTO;
import br.com.band.band.financeiro.application.dto.MonthlySummaryDTO;
import br.com.band.band.financeiro.application.dto.TransactionDTO;
import br.com.band.band.financeiro.domain.model.TransactionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final FinanceiroService service;

    public FinanceiroController(FinanceiroService service) {
        this.service = service;
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> listByMonth(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return service.listByMonth(year, month);
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody TransactionRequest req) {
        return service.create(
                TransactionType.valueOf(req.type()),
                req.description(),
                req.amount(),
                LocalDate.parse(req.dueDate()),
                req.category(),
                req.notes()
        );
    }

    @PutMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody TransactionRequest req) {
        service.update(
                id,
                TransactionType.valueOf(req.type()),
                req.description(),
                req.amount(),
                LocalDate.parse(req.dueDate()),
                req.category(),
                req.notes()
        );
    }

    @PatchMapping("/transactions/{id}/pay")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markAsPaid(@PathVariable UUID id, @RequestBody(required = false) PayRequest req) {
        LocalDate paymentDate = (req != null && req.paymentDate() != null)
                ? LocalDate.parse(req.paymentDate())
                : null;
        service.markAsPaid(id, paymentDate);
    }

    @DeleteMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping("/summary")
    public MonthlySummaryDTO summary(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return service.getMonthlySummary(year, month);
    }

    @GetMapping("/projections")
    public List<MonthlyProjectionDTO> projections(
            @RequestParam(defaultValue = "6") int months
    ) {
        return service.getMonthlyProjections(months);
    }

    record TransactionRequest(
            String type,
            String description,
            BigDecimal amount,
            String dueDate,
            String category,
            String notes
    ) {}

    record PayRequest(String paymentDate) {}
}
