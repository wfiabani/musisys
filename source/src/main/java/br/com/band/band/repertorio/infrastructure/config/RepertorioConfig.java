package br.com.band.band.repertorio.infrastructure.config;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.music.usecase.ListAllMusicsUseCase;
import br.com.band.band.repertorio.domain.music.repository.MusicRepository;
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
    public RepertorioService repertorioService(
            ListAllMusicsUseCase listAllMusicsUseCase
    ) {
        return new RepertorioService(listAllMusicsUseCase);
    }

}
