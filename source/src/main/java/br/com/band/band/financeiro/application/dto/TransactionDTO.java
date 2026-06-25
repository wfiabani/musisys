package br.com.band.band.financeiro.application.dto;

import br.com.band.band.financeiro.domain.model.TransactionStatus;
import br.com.band.band.financeiro.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        TransactionType type,
        String description,
        BigDecimal amount,
        LocalDate dueDate,
        LocalDate paymentDate,
        TransactionStatus status,
        String category,
        String notes
) {}
