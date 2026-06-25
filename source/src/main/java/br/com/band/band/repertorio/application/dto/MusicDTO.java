package br.com.band.band.repertorio.application.dto;

import java.util.UUID;

public record MusicDTO(
        UUID id,
        String title,
        String key,
        String author
) {
}
