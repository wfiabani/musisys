package br.com.band.band.financeiro.application.dto;

import java.math.BigDecimal;

public record MonthlyProjectionDTO(
        int year,
        int month,
        String monthLabel,
        BigDecimal income,
        BigDecimal expenses,
        BigDecimal balance
) {}
