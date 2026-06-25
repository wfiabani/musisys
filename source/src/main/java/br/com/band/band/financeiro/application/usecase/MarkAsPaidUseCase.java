package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.exception.TransactionNotFoundException;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.UUID;

public class MarkAsPaidUseCase {

    private final TransactionRepository repository;

    public MarkAsPaidUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, LocalDate paymentDate) {
        var t = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        t.markAsPaid(paymentDate != null ? paymentDate : LocalDate.now());
        repository.save(t);
    }
}
