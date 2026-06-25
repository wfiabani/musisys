package br.com.band.band.financeiro.infrastructure.persistence;

import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.domain.model.Transaction;
import br.com.band.band.financeiro.infrastructure.persistence.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final SpringDataTransactionRepository spring;

    public JpaTransactionRepository(SpringDataTransactionRepository spring) {
        this.spring = spring;
    }

    @Override
    public List<Transaction> findByYearAndMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end   = start.withDayOfMonth(start.lengthOfMonth());
        return spring.findByDueDateBetween(start, end).stream()
                .map(TransactionMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return spring.findById(id).map(TransactionMapper::toDomain);
    }

    @Override
    public void save(Transaction t) {
        spring.save(TransactionMapper.toEntity(t));
    }

    @Override
    public void deleteById(UUID id) {
        spring.deleteById(id);
    }
}
