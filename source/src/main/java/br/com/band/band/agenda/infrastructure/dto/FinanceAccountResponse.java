package br.com.band.band.agenda.infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record FinanceAccountResponse(
        UUID id,
        String description,
        String type,
        String category,
        String status,
        LocalDate dueDate,
        BigDecimal amount
) {}
