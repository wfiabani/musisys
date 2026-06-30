package br.com.band.band.agenda.infrastructure.client;

import br.com.band.band.agenda.infrastructure.dto.FinanceAccountResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class FinanceiroClient {

    private final RestClient restClient;

    public FinanceiroClient(RestClient.Builder builder, String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public List<FinanceAccountResponse> getTransactions(int year, int month) {
        return restClient.get()
                .uri("/financeiro/transactions?year={y}&month={m}", year, month)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
