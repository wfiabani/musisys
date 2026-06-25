package br.com.band.band.financeiro.infrastructure.persistence;

import br.com.band.band.financeiro.domain.model.TransactionStatus;
import br.com.band.band.financeiro.domain.model.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "financial_transactions")
public class TransactionEntity {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    private String category;

    @Column(length = 1000)
    private String notes;

    protected TransactionEntity() {}

    public TransactionEntity(UUID id, TransactionType type, String description, BigDecimal amount,
                             LocalDate dueDate, LocalDate paymentDate, TransactionStatus status,
                             String category, String notes) {
        this.id          = id;
        this.type        = type;
        this.description = description;
        this.amount      = amount;
        this.dueDate     = dueDate;
        this.paymentDate = paymentDate;
        this.status      = status;
        this.category    = category;
        this.notes       = notes;
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
