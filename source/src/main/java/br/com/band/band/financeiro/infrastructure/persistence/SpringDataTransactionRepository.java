package br.com.band.band.financeiro.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findByDueDateBetween(LocalDate start, LocalDate end);
}
