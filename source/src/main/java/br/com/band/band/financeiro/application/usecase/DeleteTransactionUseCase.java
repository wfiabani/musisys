package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.exception.TransactionNotFoundException;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;

import java.util.UUID;

public class DeleteTransactionUseCase {

    private final TransactionRepository repository;

    public DeleteTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        repository.deleteById(id);
    }
}
