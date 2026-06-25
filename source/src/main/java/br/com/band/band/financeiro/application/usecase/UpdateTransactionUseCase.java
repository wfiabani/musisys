package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.exception.TransactionNotFoundException;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class UpdateTransactionUseCase {

    private final TransactionRepository repository;

    public UpdateTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, TransactionType type, String description, BigDecimal amount,
                        LocalDate dueDate, String category, String notes) {
        var t = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        t.update(type, description, amount, dueDate, category, notes);
        repository.save(t);
    }
}
