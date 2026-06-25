package br.com.band.band.financeiro.application.port.repository;

import br.com.band.band.financeiro.domain.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    List<Transaction> findByYearAndMonth(int year, int month);

    Optional<Transaction> findById(UUID id);

    void save(Transaction transaction);

    void deleteById(UUID id);
}
