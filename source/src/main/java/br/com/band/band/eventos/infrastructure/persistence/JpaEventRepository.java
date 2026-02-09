package br.com.band.band.eventos.infrastructure.persistence;

import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.infrastructure.persistence.mapper.EventMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                .map(EventMapper::toDomain)   // ✅ AQUI ESTÁ A CORREÇÃO
                .toList();
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return repository.findById(id)
                .map(EventMapper::toDomain);
    }

    @Override
    public List<Event> findBySetlistId(UUID id) {
        return repository.findBySetlistId(id).stream().map(EventMapper::toDomain).toList();
    }

    @Override
    public void saveAll(List<Event> events) {
        List<EventEntity> entities = events.stream()
                .map(EventMapper::toEntity)
                .toList();

        repository.saveAll(entities);
    }

    @Override
    public void save(Event event) {
        repository.save(EventMapper.toEntity(event));
    }
}
