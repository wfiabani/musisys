package br.com.band.band.profissionais.infrastructure.config;

import br.com.band.band.profissionais.application.ProfissionaisService;
import br.com.band.band.profissionais.application.port.DomainEventPublisher;
import br.com.band.band.profissionais.application.port.EventosClient;
import br.com.band.band.profissionais.application.port.repository.ProfissionalRepository;
import br.com.band.band.profissionais.application.usecase.CreateProfissionalUseCase;
import br.com.band.band.profissionais.application.usecase.DeleteProfissionalUseCase;
import br.com.band.band.profissionais.application.usecase.ListAllProfissionaisUseCase;
import br.com.band.band.profissionais.application.usecase.UpdateProfissionalUseCase;
import br.com.band.band.profissionais.infrastructure.client.RestEventosClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProfissionaisConfig {

    @Value("${app.eventos.base-url}")
    private String eventosBaseUrl;

    @Bean
    public ListAllProfissionaisUseCase listAllProfissionaisUseCase(ProfissionalRepository repository) {
        return new ListAllProfissionaisUseCase(repository);
    }

    @Bean
    public CreateProfissionalUseCase createProfissionalUseCase(ProfissionalRepository repository) {
        return new CreateProfissionalUseCase(repository);
    }

    @Bean
    public UpdateProfissionalUseCase updateProfissionalUseCase(ProfissionalRepository repository) {
        return new UpdateProfissionalUseCase(repository);
    }

    @Bean
    public DeleteProfissionalUseCase deleteProfissionalUseCase(
            ProfissionalRepository repository,
            EventosClient eventosClient,
            DomainEventPublisher eventPublisher
    ) {
        return new DeleteProfissionalUseCase(repository, eventosClient, eventPublisher);
    }

    @Bean
    public ProfissionaisService profissionaisService(
            ListAllProfissionaisUseCase listAllProfissionaisUseCase,
            CreateProfissionalUseCase createProfissionalUseCase,
            UpdateProfissionalUseCase updateProfissionalUseCase,
            DeleteProfissionalUseCase deleteProfissionalUseCase
    ) {
        return new ProfissionaisService(
                listAllProfissionaisUseCase,
                createProfissionalUseCase,
                updateProfissionalUseCase,
                deleteProfissionalUseCase
        );
    }

    @Bean
    public EventosClient profissionaisEventosClient(RestTemplate restTemplate) {
        return new RestEventosClient(restTemplate, eventosBaseUrl);
    }
}
