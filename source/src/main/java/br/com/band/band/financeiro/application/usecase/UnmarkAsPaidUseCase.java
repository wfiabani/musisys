package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.exception.TransactionNotFoundException;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;

import java.util.UUID;

public class UnmarkAsPaidUseCase {

    private final TransactionRepository repository;

    public UnmarkAsPaidUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        var t = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        t.unmarkAsPaid();
        repository.save(t);
    }
}
