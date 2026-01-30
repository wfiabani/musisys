package br.com.band.band.eventos.infrastructure.client;

import br.com.band.band.eventos.application.dto.SetlistDto;
import br.com.band.band.eventos.application.port.SetlistClient;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

public class RestSetlistClient implements SetlistClient {

    private final RestTemplate restTemplate;
    private final String repertorioBaseUrl;

    public RestSetlistClient(
            RestTemplate restTemplate,
            String repertorioBaseUrl
    ) {
        this.restTemplate = restTemplate;
        this.repertorioBaseUrl = repertorioBaseUrl;
    }

    @Override
    public Optional<SetlistDto> findById(UUID setlistId) {
        try {
            String url = repertorioBaseUrl + "/repertorio/setlist/" + setlistId;
            SetlistDto response = restTemplate.getForObject(url, SetlistDto.class);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
