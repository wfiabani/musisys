package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import br.com.band.band.repertorio.domain.model.Setlist;
import br.com.band.band.repertorio.application.port.repository.SetlistRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaSetlistRepository implements SetlistRepository {

    private final SpringDataSetlistRepository repository;
    private final SpringDataSetlistItemRepository itemRepository;

    public JpaSetlistRepository(
            SpringDataSetlistRepository repository,
            SpringDataSetlistItemRepository itemRepository
    ) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Setlist> findAll() {
        return repository.findAllWithItems()
                .stream()
                .map(entity -> {
                    Setlist setlist = new Setlist(entity.getId(), entity.getName());
                    entity.getItems().stream()
                            .sorted(Comparator.comparingInt(SetlistItemEntity::getPosition))
                            .forEach(item -> setlist.addMusic(item.getMusicId()));
                    return setlist;
                })
                .toList();
    }

    @Override
    public Optional<Setlist> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> {
                    Setlist setlist = new Setlist(entity.getId(), entity.getName());
                    entity.getItems().stream()
                            .sorted(Comparator.comparingInt(SetlistItemEntity::getPosition))
                            .forEach(item -> setlist.addMusic(item.getMusicId()));
                    return setlist;
                });
    }

    @Override
    @Transactional
    public void save(Setlist setlist) {
        // 1. Upsert the setlist row (id + name)
        repository.save(new SetlistEntity(setlist.getId(), setlist.getName()));

        // 2. Delete all existing items for this setlist
        itemRepository.deleteBySetlistId(setlist.getId());

        // 3. Insert updated items
        if (!setlist.getItems().isEmpty()) {
            SetlistEntity ref = repository.getReferenceById(setlist.getId());
            List<SetlistItemEntity> newItems = setlist.getItems().stream()
                    .map(item -> new SetlistItemEntity(item, ref))
                    .toList();
            itemRepository.saveAll(newItems);
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        itemRepository.deleteBySetlistId(id);
        repository.deleteById(id);
    }
}
