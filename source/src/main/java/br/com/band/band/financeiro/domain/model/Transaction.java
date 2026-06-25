package br.com.band.band.financeiro.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private TransactionType type;
    private String description;
    private BigDecimal amount;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private TransactionStatus status;
    private String category;
    private String notes;

    public Transaction(
            UUID id,
            TransactionType type,
            String description,
            BigDecimal amount,
            LocalDate dueDate,
            LocalDate paymentDate,
            TransactionStatus status,
            String category,
            String notes
    ) {
        this.id          = Objects.requireNonNull(id);
        this.type        = Objects.requireNonNull(type);
        this.description = Objects.requireNonNull(description);
        this.amount      = Objects.requireNonNull(amount);
        this.dueDate     = Objects.requireNonNull(dueDate);
        this.paymentDate = paymentDate;
        this.status      = Objects.requireNonNull(status);
        this.category    = category;
        this.notes       = notes;
    }

    public void update(TransactionType type, String description, BigDecimal amount,
                       LocalDate dueDate, String category, String notes) {
        this.type        = Objects.requireNonNull(type);
        this.description = Objects.requireNonNull(description);
        this.amount      = Objects.requireNonNull(amount);
        this.dueDate     = Objects.requireNonNull(dueDate);
        this.category    = category;
        this.notes       = notes;
    }

    public void markAsPaid(LocalDate paymentDate) {
        this.status      = TransactionStatus.PAID;
        this.paymentDate = paymentDate;
    }

    public boolean isOverdue() {
        return status == TransactionStatus.PENDING && dueDate.isBefore(LocalDate.now());
    }

    public UUID getId()                  { return id; }
    public TransactionType getType()     { return type; }
    public String getDescription()       { return description; }
    public BigDecimal getAmount()        { return amount; }
    public LocalDate getDueDate()        { return dueDate; }
    public LocalDate getPaymentDate()    { return paymentDate; }
    public TransactionStatus getStatus() { return status; }
    public String getCategory()          { return category; }
    public String getNotes()             { return notes; }
}
