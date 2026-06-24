package br.com.band.band.repertorio.infrastructure.config;

import br.com.band.band.repertorio.application.RepertorioService;
import br.com.band.band.repertorio.application.port.DomainEventPublisher;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import br.com.band.band.repertorio.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepertorioConfig {

    @Bean
    public ListAllMusicsUseCase listAllMusicsUseCase(MusicRepository musicRepository) {
        return new ListAllMusicsUseCase(musicRepository);
    }

    @Bean
    public CreateMusicUseCase createMusicUseCase(MusicRepository musicRepository) {
        return new CreateMusicUseCase(musicRepository);
    }

    @Bean
    public UpdateMusicUseCase updateMusicUseCase(MusicRepository musicRepository) {
        return new UpdateMusicUseCase(musicRepository);
    }

    @Bean
    public DeleteMusicUseCase deleteMusicUseCase(MusicRepository musicRepository) {
        return new DeleteMusicUseCase(musicRepository);
    }

    @Bean
    public ListAllSetlistsUseCase listAllSetlistsUseCase(SetlistRepository setlistRepository) {
        return new ListAllSetlistsUseCase(setlistRepository);
    }

    @Bean
    public GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase(
            SetlistRepository setlistRepository,
            MusicRepository musicRepository
    ) {
        return new GetSetlistWithMusicsUseCase(setlistRepository, musicRepository);
    }

    @Bean
    public CreateSetlistUseCase createSetlistUseCase(SetlistRepository setlistRepository) {
        return new CreateSetlistUseCase(setlistRepository);
    }

    @Bean
    public RemoveSetlistUseCase removeSetlistUseCase(
            SetlistRepository repository,
            DomainEventPublisher eventPublisher
    ) {
        return new RemoveSetlistUseCase(repository, eventPublisher);
    }

    @Bean
    public AddMusicToSetlistUseCase addMusicToSetlistUseCase(SetlistRepository setlistRepository) {
        return new AddMusicToSetlistUseCase(setlistRepository);
    }

    @Bean
    public RemoveMusicFromSetlistUseCase removeMusicFromSetlistUseCase(SetlistRepository setlistRepository) {
        return new RemoveMusicFromSetlistUseCase(setlistRepository);
    }

    @Bean
    public ReorderSetlistUseCase reorderSetlistUseCase(SetlistRepository setlistRepository) {
        return new ReorderSetlistUseCase(setlistRepository);
    }

    @Bean
    public RepertorioService repertorioService(
            ListAllMusicsUseCase listAllMusicsUseCase,
            CreateMusicUseCase createMusicUseCase,
            UpdateMusicUseCase updateMusicUseCase,
            DeleteMusicUseCase deleteMusicUseCase,
            ListAllSetlistsUseCase listAllSetlistsUseCase,
            GetSetlistWithMusicsUseCase getSetlistWithMusicsUseCase,
            CreateSetlistUseCase createSetlistUseCase,
            RemoveSetlistUseCase removeSetlistUseCase,
            AddMusicToSetlistUseCase addMusicToSetlistUseCase,
            RemoveMusicFromSetlistUseCase removeMusicFromSetlistUseCase,
            ReorderSetlistUseCase reorderSetlistUseCase
    ) {
        return new RepertorioService(
                listAllMusicsUseCase,
                createMusicUseCase,
                updateMusicUseCase,
                deleteMusicUseCase,
                listAllSetlistsUseCase,
                getSetlistWithMusicsUseCase,
                createSetlistUseCase,
                removeSetlistUseCase,
                addMusicToSetlistUseCase,
                removeMusicFromSetlistUseCase,
                reorderSetlistUseCase
        );
    }
}
