package br.com.band.band.profissionais.infrastructure.client;

import br.com.band.band.profissionais.application.port.EventosClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RestEventosClient implements EventosClient {

    private final RestTemplate restTemplate;
    private final String eventosBaseUrl;

    public RestEventosClient(RestTemplate restTemplate, String eventosBaseUrl) {
        this.restTemplate = restTemplate;
        this.eventosBaseUrl = eventosBaseUrl;
    }

    @Override
    public boolean hasPastEventAssignment(UUID professionalId) {
        try {
            var response = restTemplate.exchange(
                    eventosBaseUrl + "/eventos",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<EventSummaryDto>>() {}
            );
            List<EventSummaryDto> events = response.getBody() != null ? response.getBody() : List.of();
            LocalDateTime now = LocalDateTime.now();

            return events.stream().anyMatch(event ->
                    event.professionalIds() != null
                            && event.professionalIds().contains(professionalId)
                            && event.dateTime().isBefore(now)
            );
        } catch (Exception e) {
            return false;
        }
    }

    private record EventSummaryDto(UUID id, LocalDateTime dateTime, List<UUID> professionalIds) {}
}
