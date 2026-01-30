package br.com.band.band.eventos.application.dto;

import java.util.List;
import java.util.UUID;

public record SetlistDto(
        UUID id,
        String name,
        List<SetlistItemDto> items
) {}
