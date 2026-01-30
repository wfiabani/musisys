package br.com.band.band.repertorio.application.dto;

import java.util.UUID;

public record SetlistItemDTO(
        UUID id,
        String title,
        String key,
        String author,
        int position
) {
}
