package br.com.band.band.eventos.infrastructure.persistence;

import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.domain.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaEventRepository implements EventRepository {

    private final SpringDataEventRepository repository;

    public JpaEventRepository(SpringDataEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    Event event = new Event(
                            entity.getId(),
                            entity.getType(),
                            entity.getDateTime(),
                            entity.getLocation(),
                            entity.getNotes()
                    );

                    if (entity.getSetlistId() != null) {
                        event.attachSetlist(entity.getSetlistId());
                    }

                    return event;
                })
                .toList();
    }
}
