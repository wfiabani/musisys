package br.com.band.band.financeiro.application.usecase;

import br.com.band.band.financeiro.application.dto.TransactionDTO;
import br.com.band.band.financeiro.application.port.repository.TransactionRepository;

import java.util.Comparator;
import java.util.List;

public class ListByMonthUseCase {

    private final TransactionRepository repository;

    public ListByMonthUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<TransactionDTO> execute(int year, int month) {
        return repository.findByYearAndMonth(year, month).stream()
                .map(t -> new TransactionDTO(
                        t.getId(), t.getType(), t.getDescription(), t.getAmount(),
                        t.getDueDate(), t.getPaymentDate(), t.getStatus(),
                        t.getCategory(), t.getNotes()
                ))
                .sorted(Comparator.comparing(TransactionDTO::dueDate))
                .toList();
    }
}
