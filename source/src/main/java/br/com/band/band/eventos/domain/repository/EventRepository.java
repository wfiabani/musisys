package br.com.band.band.eventos.domain.repository;

import br.com.band.band.eventos.domain.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();
}
