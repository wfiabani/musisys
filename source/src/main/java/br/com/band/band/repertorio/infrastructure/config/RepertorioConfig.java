package br.com.band.band.repertorio.infrastructure.config;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.usecase.GetSetlistWithMusicsUseCase;
import br.com.band.band.repertorio.application.usecase.ListAllMusicsUseCase;
import br.com.band.band.repertorio.domain.repository.MusicRepository;
import br.com.band.band.repertorio.domain.repository.SetlistRepository;
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
    public RepertorioService repertorioService(
            ListAllMusicsUseCase listAllMusicsUseCase,
            GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase
    ) {
        return new RepertorioService(listAllMusicsUseCase, getSetlistWithMusicsUseCase);
    }

}
