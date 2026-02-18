package br.com.band.band.agenda.infrastructure.client;

import br.com.band.band.agenda.infrastructure.dto.FinanceAccountResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class FinanceiroClient {

    private final RestClient restClient;

    public FinanceiroClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public List<FinanceAccountResponse> getAccounts() {
        return restClient.get()
                .uri("/financeiro/accounts")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<>() {});
    }
}
