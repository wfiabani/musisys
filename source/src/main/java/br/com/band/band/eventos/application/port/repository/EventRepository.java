package br.com.band.band.eventos.application.port.repository;

import br.com.band.band.eventos.domain.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    List<Event> findAll();

    Optional<Event> findById(UUID id);

    List<Event> findBySetlistId(UUID id);

    void saveAll(List<Event> events);

    void save(Event event);
}
