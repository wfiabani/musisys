package br.com.band.band.eventos.infrastructure.persistence;

import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.application.port.repository.EventRepository;
import br.com.band.band.eventos.infrastructure.persistence.mapper.EventMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaEventRepository implements EventRepository {

    private final SpringDataEventRepository repository;
    private final SpringDataEventProfessionalRepository professionalRepository;

    public JpaEventRepository(
            SpringDataEventRepository repository,
            SpringDataEventProfessionalRepository professionalRepository
    ) {
        this.repository = repository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public List<Event> findAll() {
        return repository.findAllWithProfessionals()
                .stream()
                .map(EventMapper::toDomain)
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
    public List<Event> findByProfessionalId(UUID professionalId) {
        return repository.findByProfessionals_ProfessionalId(professionalId).stream()
                .map(EventMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void saveAll(List<Event> events) {
        events.forEach(this::save);
    }

    @Override
    @Transactional
    public void save(Event event) {
        repository.save(EventMapper.toEntity(event));

        professionalRepository.deleteByEventId(event.getId());

        if (!event.getProfessionalIds().isEmpty()) {
            EventEntity ref = repository.getReferenceById(event.getId());
            List<EventProfessionalEntity> entities = event.getProfessionalIds().stream()
                    .map(professionalId -> new EventProfessionalEntity(professionalId, ref))
                    .toList();
            professionalRepository.saveAll(entities);
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        professionalRepository.deleteByEventId(id);
        repository.deleteById(id);
    }
}
