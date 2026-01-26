package br.com.band.band.repertorio.infrastructure.persistence.music;

import br.com.band.band.repertorio.domain.music.model.Music;
import br.com.band.band.repertorio.domain.music.repository.MusicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
