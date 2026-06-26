package br.com.band.band.financeiro.infrastructure.config;

import br.com.band.band.financeiro.application.FinanceiroService;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinanceiroConfig {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(TransactionRepository repo) {
        return new CreateTransactionUseCase(repo);
    }

    @Bean
    public UpdateTransactionUseCase updateTransactionUseCase(TransactionRepository repo) {
        return new UpdateTransactionUseCase(repo);
    }

    @Bean
    public DeleteTransactionUseCase deleteTransactionUseCase(TransactionRepository repo) {
        return new DeleteTransactionUseCase(repo);
    }

    @Bean
    public MarkAsPaidUseCase markAsPaidUseCase(TransactionRepository repo) {
        return new MarkAsPaidUseCase(repo);
    }

    @Bean
    public UnmarkAsPaidUseCase unmarkAsPaidUseCase(TransactionRepository repo) {
        return new UnmarkAsPaidUseCase(repo);
    }

    @Bean
    public ListByMonthUseCase listByMonthUseCase(TransactionRepository repo) {
        return new ListByMonthUseCase(repo);
    }

    @Bean
    public GetMonthlySummaryUseCase getMonthlySummaryUseCase(TransactionRepository repo) {
        return new GetMonthlySummaryUseCase(repo);
    }

    @Bean
    public GetMonthlyProjectionsUseCase getMonthlyProjectionsUseCase(TransactionRepository repo) {
        return new GetMonthlyProjectionsUseCase(repo);
    }

    @Bean
    public FinanceiroService financeiroService(
            CreateTransactionUseCase createTransaction,
            UpdateTransactionUseCase updateTransaction,
            DeleteTransactionUseCase deleteTransaction,
            MarkAsPaidUseCase markAsPaid,
            UnmarkAsPaidUseCase unmarkAsPaid,
            ListByMonthUseCase listByMonth,
            GetMonthlySummaryUseCase getMonthlySummary,
            GetMonthlyProjectionsUseCase getMonthlyProjections
    ) {
        return new FinanceiroService(
                createTransaction, updateTransaction, deleteTransaction,
                markAsPaid, unmarkAsPaid, listByMonth, getMonthlySummary, getMonthlyProjections
        );
    }
}
