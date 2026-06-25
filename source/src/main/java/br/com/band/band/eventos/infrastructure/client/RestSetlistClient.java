package br.com.band.band.eventos.infrastructure.client;

import br.com.band.band.eventos.application.dto.SetlistDto;
import br.com.band.band.eventos.application.dto.SetlistSummaryDto;
import br.com.band.band.eventos.application.port.SetlistClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RestSetlistClient implements SetlistClient {

    private final RestTemplate restTemplate;
    private final String repertorioBaseUrl;

    public RestSetlistClient(RestTemplate restTemplate, String repertorioBaseUrl) {
        this.restTemplate = restTemplate;
        this.repertorioBaseUrl = repertorioBaseUrl;
    }

    @Override
    public List<SetlistSummaryDto> findAll() {
        try {
            var response = restTemplate.exchange(
                    repertorioBaseUrl + "/repertorio/setlists",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<SetlistSummaryDto>>() {}
            );
            return response.getBody() != null ? response.getBody() : List.of();
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public Optional<SetlistDto> findById(UUID setlistId) {
        try {
            SetlistDto response = restTemplate.getForObject(
                    repertorioBaseUrl + "/repertorio/setlists/" + setlistId,
                    SetlistDto.class
            );
            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
