package br.com.band.band.financeiro.infrastructure.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/financeiro/accounts")
public class FinanceiroController {

    @GetMapping
    public List<FinanceAccountResponse> listAccounts() {
        return List.of(
                new FinanceAccountResponse(
                        UUID.randomUUID(),
                        "Cachê Show São Paulo",
                        AccountType.RECEIVABLE,
                        AccountCategory.ONE_TIME,
                        AccountStatus.OPEN,
                        LocalDate.now().plusDays(20)
                ),
                new FinanceAccountResponse(
                        UUID.randomUUID(),
                        "Aluguel Estúdio",
                        AccountType.PAYABLE,
                        AccountCategory.RECURRING,
                        AccountStatus.PAID,
                        LocalDate.now().minusDays(5)
                )
        );
    }
}

record FinanceAccountResponse(
        UUID id,
        String description,
        AccountType type,
        AccountCategory category,
        AccountStatus status,
        LocalDate dueDate
) {}

enum AccountType {
    PAYABLE,
    RECEIVABLE
}

enum AccountCategory {
    ONE_TIME,
    RECURRING
}

enum AccountStatus {
    OPEN,
    PARTIALLY_PAID,
    PAID
}

