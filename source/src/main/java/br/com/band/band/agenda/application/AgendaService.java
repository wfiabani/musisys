package br.com.band.band.agenda.application;

import br.com.band.band.agenda.domain.model.AgendaItem;
import br.com.band.band.agenda.domain.model.AgendaItemType;
import br.com.band.band.agenda.infrastructure.client.EventosClient;
import br.com.band.band.agenda.infrastructure.client.FinanceiroClient;
import br.com.band.band.agenda.infrastructure.dto.EventoResponse;
import br.com.band.band.agenda.infrastructure.dto.FinanceAccountResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendaService {

    private final EventosClient eventosClient;
    private final FinanceiroClient financeiroClient;

    public AgendaService(EventosClient eventosClient,
                         FinanceiroClient financeiroClient) {
        this.eventosClient = eventosClient;
        this.financeiroClient = financeiroClient;
    }

    public List<AgendaItem> getAgendaByDate(LocalDate date) {

        List<AgendaItem> eventos = eventosClient.getEventos()
                .stream()
                .filter(e -> e.dateTime().toLocalDate().equals(date))
                .map(this::mapEvento)
                .toList();

        List<AgendaItem> financeiros = financeiroClient.getAccounts()
                .stream()
                .filter(f -> f.dueDate().equals(date))
                .map(this::mapFinanceiro)
                .toList();

        return List.of(eventos, financeiros)
                .stream()
                .flatMap(List::stream)
                .sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime()))
                .toList();
    }

    private AgendaItem mapEvento(EventoResponse e) {
        return new AgendaItem(
                e.id(),
                AgendaItemType.EVENT,
                e.type() + " - " + e.location(),
                e.dateTime()
        );
    }

    private AgendaItem mapFinanceiro(FinanceAccountResponse f) {
        return new AgendaItem(
                f.id(),
                AgendaItemType.FINANCIAL,
                f.description(),
                f.dueDate().atStartOfDay()
        );
    }
}
