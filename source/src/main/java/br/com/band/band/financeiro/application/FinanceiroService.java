package br.com.band.band.financeiro.application;

import br.com.band.band.financeiro.application.dto.MonthlyProjectionDTO;
import br.com.band.band.financeiro.application.dto.MonthlySummaryDTO;
import br.com.band.band.financeiro.application.dto.TransactionDTO;
import br.com.band.band.financeiro.application.usecase.*;
import br.com.band.band.financeiro.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FinanceiroService {

    private final CreateTransactionUseCase createTransaction;
    private final UpdateTransactionUseCase updateTransaction;
    private final DeleteTransactionUseCase deleteTransaction;
    private final MarkAsPaidUseCase markAsPaid;
    private final UnmarkAsPaidUseCase unmarkAsPaid;
    private final ListByMonthUseCase listByMonth;
    private final GetMonthlySummaryUseCase getMonthlySummary;
    private final GetMonthlyProjectionsUseCase getMonthlyProjections;

    public FinanceiroService(
            CreateTransactionUseCase createTransaction,
            UpdateTransactionUseCase updateTransaction,
            DeleteTransactionUseCase deleteTransaction,
            MarkAsPaidUseCase markAsPaid,
            UnmarkAsPaidUseCase unmarkAsPaid,
            ListByMonthUseCase listByMonth,
            GetMonthlySummaryUseCase getMonthlySummary,
            GetMonthlyProjectionsUseCase getMonthlyProjections
    ) {
        this.createTransaction    = createTransaction;
        this.updateTransaction    = updateTransaction;
        this.deleteTransaction    = deleteTransaction;
        this.markAsPaid           = markAsPaid;
        this.unmarkAsPaid         = unmarkAsPaid;
        this.listByMonth          = listByMonth;
        this.getMonthlySummary    = getMonthlySummary;
        this.getMonthlyProjections= getMonthlyProjections;
    }

    public UUID create(TransactionType type, String description, BigDecimal amount,
                       LocalDate dueDate, String category, String notes) {
        return createTransaction.execute(type, description, amount, dueDate, category, notes);
    }

    public void update(UUID id, TransactionType type, String description, BigDecimal amount,
                       LocalDate dueDate, String category, String notes) {
        updateTransaction.execute(id, type, description, amount, dueDate, category, notes);
    }

    public void delete(UUID id) {
        deleteTransaction.execute(id);
    }

    public void markAsPaid(UUID id, LocalDate paymentDate) {
        markAsPaid.execute(id, paymentDate);
    }

    public void unmarkAsPaid(UUID id) {
        unmarkAsPaid.execute(id);
    }

    public List<TransactionDTO> listByMonth(int year, int month) {
        return listByMonth.execute(year, month);
    }

    public MonthlySummaryDTO getMonthlySummary(int year, int month) {
        return getMonthlySummary.execute(year, month);
    }

    public List<MonthlyProjectionDTO> getMonthlyProjections(int months) {
        return getMonthlyProjections.execute(months);
    }
}
