package br.com.band.band.repertorio.application.dto;

import java.util.List;
import java.util.UUID;

public record SetlistDTO(
        UUID id,
        String name,
        List<SetlistItemDTO> items
) {
}
