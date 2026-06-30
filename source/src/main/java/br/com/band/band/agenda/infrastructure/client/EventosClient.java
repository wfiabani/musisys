package br.com.band.band.agenda.infrastructure.client;

import br.com.band.band.agenda.infrastructure.dto.EventoResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class EventosClient {

    private final RestClient restClient;

    public EventosClient(RestClient.Builder builder, String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public List<EventoResponse> getEventos() {
        return restClient.get()
                .uri("/eventos")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
