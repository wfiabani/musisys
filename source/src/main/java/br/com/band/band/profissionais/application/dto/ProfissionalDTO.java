package br.com.band.band.profissionais.application.dto;

import java.util.UUID;

public record ProfissionalDTO(
        UUID id,
        String name,
        String role,
        String description,
        boolean isDefault
) {
}
