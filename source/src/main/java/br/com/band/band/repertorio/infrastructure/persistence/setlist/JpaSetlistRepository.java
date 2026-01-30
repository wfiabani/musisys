package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.domain.model.SetlistItem;
import br.com.band.band.repertorio.domain.repository.SetlistRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaSetlistRepository implements SetlistRepository {

    private final SpringDataSetlistRepository repository;

    public JpaSetlistRepository(SpringDataSetlistRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Setlist> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {

                    List<SetlistItem> items = entity.getItems()
                            .stream()
                            .map(item ->
                                    new SetlistItem(
                                            item.getMusicId(),
                                            item.getPosition()
                                    )
                            )
                            .toList();

                    return new Setlist(
                            entity.getId(),
                            entity.getName()
                    );
                })
                .toList();
    }

    @Override
    public Optional<Setlist> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> {

                    Setlist setlist = new Setlist(
                            entity.getId(),
                            entity.getName()
                    );

                    entity.getItems().stream()
                            .sorted(Comparator.comparingInt(SetlistItemEntity::getPosition))
                            .forEach(item ->
                                    setlist.addMusic(item.getMusicId())
                            );

                    return setlist;
                });
    }

    @Override
    public void save(Setlist setlist) {
        repository.save(new SetlistEntity(setlist));
    }
}
