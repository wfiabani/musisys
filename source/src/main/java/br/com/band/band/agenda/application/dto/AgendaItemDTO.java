package br.com.band.band.agenda.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgendaItemDTO(
        UUID id,
        String type,
        String title,
        LocalDateTime dateTime,
        String location,
        String eventType,
        BigDecimal amount,
        String status,
        String transactionType
) {}
