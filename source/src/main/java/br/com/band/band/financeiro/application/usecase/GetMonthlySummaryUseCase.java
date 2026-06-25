package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.dto.MonthlySummaryDTO;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.domain.model.Transaction;
import br.com.band.band.financeiro.domain.model.TransactionStatus;
import br.com.band.band.financeiro.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GetMonthlySummaryUseCase {

    private final TransactionRepository repository;

    public GetMonthlySummaryUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public MonthlySummaryDTO execute(int year, int month) {
        List<Transaction> all = repository.findByYearAndMonth(year, month).stream()
                .filter(t -> t.getStatus() != TransactionStatus.CANCELLED)
                .toList();

        LocalDate today = LocalDate.now();

        List<Transaction> paid    = filter(all, null, TransactionStatus.PAID);
        List<Transaction> pending = filter(all, null, TransactionStatus.PENDING);

        BigDecimal totalIncome    = sum(all,     TransactionType.INCOME);
        BigDecimal totalExpenses  = sum(all,     TransactionType.EXPENSE);
        BigDecimal paidIncome     = sum(paid,    TransactionType.INCOME);
        BigDecimal paidExpenses   = sum(paid,    TransactionType.EXPENSE);
        BigDecimal pendingIncome  = sum(pending, TransactionType.INCOME);
        BigDecimal pendingExpenses= sum(pending, TransactionType.EXPENSE);

        long overdueCount = pending.stream()
                .filter(t -> t.getDueDate().isBefore(today))
                .count();

        return new MonthlySummaryDTO(
                year, month,
                totalIncome, totalExpenses, totalIncome.subtract(totalExpenses),
                paidIncome, paidExpenses, paidIncome.subtract(paidExpenses),
                pendingIncome, pendingExpenses,
                all.size(), pending.size(), overdueCount
        );
    }

    private List<Transaction> filter(List<Transaction> list, TransactionType type, TransactionStatus status) {
        return list.stream()
                .filter(t -> type   == null || t.getType()   == type)
                .filter(t -> status == null || t.getStatus() == status)
                .toList();
    }

    private BigDecimal sum(List<Transaction> list, TransactionType type) {
        return list.stream()
                .filter(t -> t.getType() == type)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
