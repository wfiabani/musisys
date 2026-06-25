package br.com.band.band.financeiro.infrastructure.persistence.mapper;

import br.com.band.band.financeiro.domain.model.Transaction;
import br.com.band.band.financeiro.infrastructure.persistence.TransactionEntity;

public class TransactionMapper {

    private TransactionMapper() {}

    public static Transaction toDomain(TransactionEntity e) {
        return new Transaction(
                e.getId(), e.getType(), e.getDescription(), e.getAmount(),
                e.getDueDate(), e.getPaymentDate(), e.getStatus(),
                e.getCategory(), e.getNotes()
        );
    }

    public static TransactionEntity toEntity(Transaction t) {
        return new TransactionEntity(
                t.getId(), t.getType(), t.getDescription(), t.getAmount(),
                t.getDueDate(), t.getPaymentDate(), t.getStatus(),
                t.getCategory(), t.getNotes()
        );
    }
}
