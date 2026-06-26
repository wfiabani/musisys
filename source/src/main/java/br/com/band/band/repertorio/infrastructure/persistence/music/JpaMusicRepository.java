package br.com.band.band.repertorio.infrastructure.persistence.music;

import br.com.band.band.repertorio.domain.model.Music;
import br.com.band.band.repertorio.application.port.repository.MusicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
                                entity.getAuthor(),
                                entity.getDescription()
                        )
                )
                .toList();
    }

    @Override
    public Optional<Music> findById(UUID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Music> findBySetlistId(UUID setlistId) {
        return repository.findBySetlistId(setlistId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void save(Music music) {
        repository.save(new MusicEntity(music.getId(), music.getTitle(), music.getKey(), music.getAuthor(), music.getDescription()));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsInAnySetlist(UUID musicId) {
        return repository.countSetlistsByMusicId(musicId) > 0;
    }

    private Music toDomain(MusicEntity entity) {
        return new Music(
                entity.getId(),
                entity.getTitle(),
                entity.getMusicalKey(),
                entity.getAuthor(),
                entity.getDescription()
        );
    }

}
