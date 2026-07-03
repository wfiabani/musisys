package br.com.band.band.eventos.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "event_professionals")
public class EventProfessionalEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID professionalId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    protected EventProfessionalEntity() {}

    public EventProfessionalEntity(UUID professionalId, EventEntity event) {
        this.professionalId = professionalId;
        this.event = event;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }
}
