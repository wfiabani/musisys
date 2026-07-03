package br.com.band.band.eventos.infrastructure.persistence.mapper;

import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.infrastructure.persistence.EventEntity;
import br.com.band.band.eventos.infrastructure.persistence.EventProfessionalEntity;

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

        event.assignProfessionals(
                entity.getProfessionals().stream()
                        .map(EventProfessionalEntity::getProfessionalId)
                        .toList()
        );

        return event;
    }

    public static EventEntity toEntity(Event event) {
        return new EventEntity(
                event.getId(),
                event.getType(),
                event.getDateTime(),
                event.getLocation(),
                event.getNotes(),
                event.getSetlistId()
        );
    }
}
