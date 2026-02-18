package br.com.band.band.agenda.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String type,
        LocalDateTime dateTime,
        String location
) {}
