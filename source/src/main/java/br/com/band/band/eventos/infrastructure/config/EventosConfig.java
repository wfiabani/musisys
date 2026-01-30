package br.com.band.band.eventos.infrastructure.config;

import br.com.band.band.eventos.application.EventosService;
import br.com.band.band.eventos.application.port.SetlistClient;
import br.com.band.band.eventos.application.usecase.GetEventWithSetlistUseCase;
import br.com.band.band.eventos.application.usecase.ListAllEventsUseCase;
import br.com.band.band.eventos.domain.repository.EventRepository;
import br.com.band.band.eventos.infrastructure.client.RestSetlistClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventosConfig {

    @Bean
    public ListAllEventsUseCase listAllEventsUseCase(
            EventRepository eventRepository
    ) {
        return new ListAllEventsUseCase(eventRepository);
    }

    @Bean
    public EventosService eventosService(
            ListAllEventsUseCase listAllEventsUseCase
    ) {
        return new EventosService(listAllEventsUseCase);
    }

    @Bean
    public GetEventWithSetlistUseCase getEventWithSetlistUseCase(
            EventRepository eventRepository,
            SetlistClient setlistClient
    ) {
        return new GetEventWithSetlistUseCase(eventRepository, setlistClient);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SetlistClient setlistClient(RestTemplate restTemplate) {
        return new RestSetlistClient(
                restTemplate,
                "http://localhost:8081"
        );
    }

}
