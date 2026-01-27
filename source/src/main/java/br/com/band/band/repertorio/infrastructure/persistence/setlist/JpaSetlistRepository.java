package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import br.com.band.band.repertorio.domain.setlist.model.Setlist;
import br.com.band.band.repertorio.domain.setlist.model.SetlistItem;
import br.com.band.band.repertorio.domain.setlist.repository.SetlistRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Setlist findById(UUID id) {
        SetlistEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Setlist not found: " + id)
                );

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
    }

    @Override
    public void save(Setlist setlist) {
        repository.save(new SetlistEntity(setlist));
    }
}
