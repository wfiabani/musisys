package br.com.band.band.eventos.infrastructure.config;

import br.com.band.band.eventos.application.EventosService;
import br.com.band.band.eventos.application.port.SetlistClient;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.application.usecase.*;
import br.com.band.band.eventos.infrastructure.client.RestSetlistClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventosConfig {

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
    public EventosService eventosService(
            ListAllEventsUseCase listAllEventsUseCase,
            GetEventWithSetlistUseCase getEventWithSetlistUseCase,
            CreateEventUseCase createEventUseCase,
            UpdateEventUseCase updateEventUseCase,
            DeleteEventUseCase deleteEventUseCase
    ) {
        return new EventosService(
                listAllEventsUseCase,
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
        return new RestSetlistClient(restTemplate, "http://localhost:8081");
    }
}
