package br.com.band.band.financeiro.application.dto;

import java.math.BigDecimal;

public record MonthlySummaryDTO(
        int year,
        int month,
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal projectedBalance,
        BigDecimal paidIncome,
        BigDecimal paidExpenses,
        BigDecimal confirmedBalance,
        BigDecimal pendingIncome,
        BigDecimal pendingExpenses,
        long totalEntries,
        long pendingCount,
        long overdueCount
) {}
