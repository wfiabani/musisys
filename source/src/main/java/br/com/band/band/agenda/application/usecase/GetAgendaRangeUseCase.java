package br.com.band.band.agenda.application.usecase;

import br.com.band.band.agenda.application.dto.AgendaItemDTO;
import br.com.band.band.agenda.infrastructure.client.EventosClient;
import br.com.band.band.agenda.infrastructure.client.FinanceiroClient;
import br.com.band.band.agenda.infrastructure.dto.FinanceAccountResponse;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Stream;

public class GetAgendaRangeUseCase {

    private final EventosClient eventosClient;
    private final FinanceiroClient financeiroClient;

    public GetAgendaRangeUseCase(EventosClient eventosClient, FinanceiroClient financeiroClient) {
        this.eventosClient    = eventosClient;
        this.financeiroClient = financeiroClient;
    }

    public List<AgendaItemDTO> execute(LocalDate start, LocalDate end) {
        List<AgendaItemDTO> eventItems    = fetchEventItems(start, end);
        List<AgendaItemDTO> financialItems = fetchFinancialItems(start, end);

        return Stream.concat(eventItems.stream(), financialItems.stream())
                .sorted(Comparator.comparing(AgendaItemDTO::dateTime))
                .toList();
    }

    private List<AgendaItemDTO> fetchEventItems(LocalDate start, LocalDate end) {
        try {
            return eventosClient.getEventos().stream()
                    .filter(e -> {
                        LocalDate d = e.dateTime().toLocalDate();
                        return !d.isBefore(start) && !d.isAfter(end);
                    })
                    .map(e -> new AgendaItemDTO(
                            e.id(), "EVENT", e.location(), e.dateTime(),
                            e.location(), e.type(), null, null, null
                    ))
                    .toList();
        } catch (Exception ex) {
            return List.of();
        }
    }

    private List<AgendaItemDTO> fetchFinancialItems(LocalDate start, LocalDate end) {
        Set<YearMonth> months = new LinkedHashSet<>();
        YearMonth cursor = YearMonth.from(start);
        YearMonth last   = YearMonth.from(end);
        while (!cursor.isAfter(last)) {
            months.add(cursor);
            cursor = cursor.plusMonths(1);
        }

        Map<UUID, FinanceAccountResponse> seen = new LinkedHashMap<>();
        for (YearMonth ym : months) {
            try {
                financeiroClient.getTransactions(ym.getYear(), ym.getMonthValue())
                        .forEach(t -> seen.putIfAbsent(t.id(), t));
            } catch (Exception ignored) {
            }
        }

        return seen.values().stream()
                .filter(t -> "PENDING".equals(t.status()))
                .filter(t -> !t.dueDate().isBefore(start) && !t.dueDate().isAfter(end))
                .map(t -> new AgendaItemDTO(
                        t.id(), "FINANCIAL", t.description(),
                        t.dueDate().atStartOfDay(),
                        null, null, t.amount(), t.status(), t.type()
                ))
                .toList();
    }
}
