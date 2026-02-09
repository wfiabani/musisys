package br.com.band.band.repertorio.infrastructure.persistence.music;

import br.com.band.band.repertorio.domain.model.Music;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaMusicRepository implements MusicRepository {

    private final SpringDataMusicRepository repository;

    public JpaMusicRepository(SpringDataMusicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Music> findAll() {
        return repository.findAll()
                .stream()
                .map(entity ->
                        new Music(
                                entity.getId(),
                                entity.getTitle(),
                                entity.getMusicalKey(),
                                entity.getAuthor()
                        )
                )
                .toList();
    }

    @Override
    public List<Music> findBySetlistId(UUID setlistId) {
        return repository.findBySetlistId(setlistId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Music toDomain(MusicEntity entity) {
        return new Music(
                entity.getId(),
                entity.getTitle(),
                entity.getMusicalKey(),
                entity.getAuthor()
        );
    }

}
