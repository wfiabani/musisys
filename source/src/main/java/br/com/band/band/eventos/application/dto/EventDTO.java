package br.com.band.band.eventos.application.dto;

import br.com.band.band.eventos.domain.model.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDTO (
        UUID id,
        EventType type,
        LocalDateTime dateTime,
        String location
){
}
