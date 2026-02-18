package br.com.band.band.agenda.infrastructure.client;

import br.com.band.band.agenda.infrastructure.dto.EventoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class EventosClient {

    private final RestClient restClient;

    public EventosClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public List<EventoResponse> getEventos() {
        return restClient.get()
                .uri("/eventos")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<>() {});
    }
}
