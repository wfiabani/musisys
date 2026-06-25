package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.dto.MonthlyProjectionDTO;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;
import br.com.band.band.financeiro.domain.model.Transaction;
import br.com.band.band.financeiro.domain.model.TransactionStatus;
import br.com.band.band.financeiro.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetMonthlyProjectionsUseCase {

    private static final String[] MONTH_NAMES =
            {"Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};

    private final TransactionRepository repository;

    public GetMonthlyProjectionsUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<MonthlyProjectionDTO> execute(int months) {
        List<MonthlyProjectionDTO> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < months; i++) {
            LocalDate ref  = today.plusMonths(i);
            int year  = ref.getYear();
            int month = ref.getMonthValue();

            List<Transaction> txList = repository.findByYearAndMonth(year, month).stream()
                    .filter(t -> t.getStatus() != TransactionStatus.CANCELLED)
                    .toList();

            BigDecimal income = txList.stream()
                    .filter(t -> t.getType() == TransactionType.INCOME)
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal expenses = txList.stream()
                    .filter(t -> t.getType() == TransactionType.EXPENSE)
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            result.add(new MonthlyProjectionDTO(
                    year, month,
                    MONTH_NAMES[month - 1] + "/" + year,
                    income, expenses, income.subtract(expenses)
            ));
        }

        return result;
    }
}
