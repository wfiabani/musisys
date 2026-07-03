package br.com.band.band.eventos.application.dto;

import java.util.UUID;

public record ProfissionalSummaryDto(UUID id, String name, String role, boolean isDefault) {}
