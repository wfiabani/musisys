package br.com.band.band.repertorio.infrastructure.config;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.port.DomainEventPublisher;
import br.com.band.band.repertorio.application.usecase.GetSetlistWithMusicsUseCase;
import br.com.band.band.repertorio.application.usecase.ListAllMusicsUseCase;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import br.com.band.band.repertorio.application.usecase.RemoveSetlistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepertorioConfig {

    @Bean
    public ListAllMusicsUseCase listAllMusicsUseCase(
            MusicRepository musicRepository
    ) {
        return new ListAllMusicsUseCase(musicRepository);
    }

    @Bean
    public GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase(
            SetlistRepository setlistRepository,
            MusicRepository musicRepository
    ) {
        return new GetSetlistWithMusicsUseCase(setlistRepository, musicRepository);
    }

    @Bean
    public RemoveSetlistUseCase removeSetlistUseCase(
            SetlistRepository repository,
            DomainEventPublisher eventPublisher
    ){
        return new RemoveSetlistUseCase(repository, eventPublisher);
    }

    @Bean
    public RepertorioService repertorioService(
            ListAllMusicsUseCase listAllMusicsUseCase,
            GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase,
            RemoveSetlistUseCase removeSetlistUseCase
    ) {
        return new RepertorioService(listAllMusicsUseCase, getSetlistWithMusicsUseCase, removeSetlistUseCase);
    }

}
