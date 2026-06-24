package br.com.band.band.repertorio.application.dto;

import java.util.UUID;

public record SetlistSummaryDTO(UUID id, String name, int itemCount) {
}
