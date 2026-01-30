package br.com.band.band.eventos.infrastructure.persistence.mapper;

import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.infrastructure.persistence.EventEntity;

public class EventMapper {

    private EventMapper() {
    }

    public static Event toDomain(EventEntity entity) {
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
    }
}
