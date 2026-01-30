package br.com.band.band.eventos.application.dto;

import java.util.UUID;

public record SetlistItemDto(
        UUID id,
        String title,
        String key,
        String author,
        int position
) {}
