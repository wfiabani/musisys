package br.com.band.band.eventos.application.usecase;

import br.com.band.band.eventos.application.dto.SetlistDto;
import br.com.band.band.eventos.domain.model.Event;
import br.com.band.band.eventos.domain.model.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventWithSetlistOutput(
        UUID id,
        EventType type,
        LocalDateTime dateTime,
        String location,
        String notes,
        SetlistDto setlist
) {

    public static EventWithSetlistOutput from(Event event, SetlistDto setlist) {
        return new EventWithSetlistOutput(
                event.getId(),
                event.getType(),
                event.getDateTime(),
                event.getLocation(),
                event.getNotes(),
                setlist
        );
    }
}
