package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.domain.model.Transaction;
import br.com.band.band.financeiro.domain.model.TransactionStatus;
import br.com.band.band.financeiro.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateTransactionUseCase {

    private final TransactionRepository repository;

    public CreateTransactionUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public UUID execute(TransactionType type, String description, BigDecimal amount,
                        LocalDate dueDate, String category, String notes) {
        Transaction t = new Transaction(
                UUID.randomUUID(), type, description, amount,
                dueDate, null, TransactionStatus.PENDING, category, notes
        );
        repository.save(t);
        return t.getId();
    }
}
