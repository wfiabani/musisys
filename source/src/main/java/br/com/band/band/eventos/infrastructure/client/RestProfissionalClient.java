package br.com.band.band.eventos.infrastructure.client;

import br.com.band.band.eventos.application.dto.ProfissionalSummaryDto;
import br.com.band.band.eventos.application.port.ProfissionalClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestProfissionalClient implements ProfissionalClient {

    private final RestTemplate restTemplate;
    private final String profissionaisBaseUrl;

    public RestProfissionalClient(RestTemplate restTemplate, String profissionaisBaseUrl) {
        this.restTemplate = restTemplate;
        this.profissionaisBaseUrl = profissionaisBaseUrl;
    }

    @Override
    public List<ProfissionalSummaryDto> findAll() {
        try {
            var response = restTemplate.exchange(
                    profissionaisBaseUrl + "/profissionais",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProfissionalSummaryDto>>() {}
            );
            return response.getBody() != null ? response.getBody() : List.of();
        } catch (Exception e) {
            return List.of();
        }
    }
}
