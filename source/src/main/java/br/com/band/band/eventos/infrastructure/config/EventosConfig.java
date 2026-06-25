package br.com.band.band.eventos.infrastructure.config;

import br.com.band.band.eventos.application.EventosService;
import br.com.band.band.eventos.application.port.SetlistClient;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.application.usecase.*;
import br.com.band.band.eventos.infrastructure.client.RestSetlistClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventosConfig {

    @Value("${app.repertorio.base-url}")
    private String repertorioBaseUrl;

    @Bean
    public ListAllEventsUseCase listAllEventsUseCase(EventRepository eventRepository) {
        return new ListAllEventsUseCase(eventRepository);
    }

    @Bean
    public GetEventWithSetlistUseCase getEventWithSetlistUseCase(
            EventRepository eventRepository,
            SetlistClient setlistClient
    ) {
        return new GetEventWithSetlistUseCase(eventRepository, setlistClient);
    }

    @Bean
    public CreateEventUseCase createEventUseCase(EventRepository eventRepository) {
        return new CreateEventUseCase(eventRepository);
    }

    @Bean
    public UpdateEventUseCase updateEventUseCase(EventRepository eventRepository) {
        return new UpdateEventUseCase(eventRepository);
    }

    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepository eventRepository) {
        return new DeleteEventUseCase(eventRepository);
    }

    @Bean
    public UpdateEventsAfterSetlistRemovalUseCase updateEventsAfterSetlistRemovalUseCase(
            EventRepository eventRepository
    ) {
        return new UpdateEventsAfterSetlistRemovalUseCase(eventRepository);
    }

    @Bean
    public ListAvailableSetlistsUseCase listAvailableSetlistsUseCase(SetlistClient setlistClient) {
        return new ListAvailableSetlistsUseCase(setlistClient);
    }

    @Bean
    public EventosService eventosService(
            ListAllEventsUseCase listAllEventsUseCase,
            ListAvailableSetlistsUseCase listAvailableSetlistsUseCase,
            GetEventWithSetlistUseCase getEventWithSetlistUseCase,
            CreateEventUseCase createEventUseCase,
            UpdateEventUseCase updateEventUseCase,
            DeleteEventUseCase deleteEventUseCase
    ) {
        return new EventosService(
                listAllEventsUseCase,
                listAvailableSetlistsUseCase,
                getEventWithSetlistUseCase,
                createEventUseCase,
                updateEventUseCase,
                deleteEventUseCase
        );
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SetlistClient setlistClient(RestTemplate restTemplate) {
        return new RestSetlistClient(restTemplate, repertorioBaseUrl);
    }
}
