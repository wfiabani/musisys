package br.com.band.band.agenda.infrastructure.config;

import br.com.band.band.agenda.application.usecase.GetAgendaRangeUseCase;
import br.com.band.band.agenda.infrastructure.client.EventosClient;
import br.com.band.band.agenda.infrastructure.client.FinanceiroClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AgendaConfig {

    @Value("${app.agenda.base-url}")
    private String agendaBaseUrl;

    @Bean
    public EventosClient eventosClient(RestClient.Builder builder) {
        return new EventosClient(builder, agendaBaseUrl);
    }

    @Bean
    public FinanceiroClient financeiroClient(RestClient.Builder builder) {
        return new FinanceiroClient(builder, agendaBaseUrl);
    }

    @Bean
    public GetAgendaRangeUseCase getAgendaRangeUseCase(EventosClient eventosClient,
                                                        FinanceiroClient financeiroClient) {
        return new GetAgendaRangeUseCase(eventosClient, financeiroClient);
    }
}
