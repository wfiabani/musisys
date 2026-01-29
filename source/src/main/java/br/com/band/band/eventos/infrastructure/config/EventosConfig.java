package br.com.band.band.eventos.infrastructure.config;

import br.com.band.band.eventos.application.EventosService;
import br.com.band.band.eventos.application.usecase.ListAllEventsUseCase;
import br.com.band.band.eventos.domain.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventosConfig {

    @Bean
    public ListAllEventsUseCase listAllEventsUseCase(
            EventRepository eventRepository
    ){
        return new ListAllEventsUseCase(eventRepository);
    }

    @Bean
    public EventosService eventosService(
            ListAllEventsUseCase listAllEventsUseCase
    ){
        return new EventosService(listAllEventsUseCase);
    }

}
