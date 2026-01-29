package br.com.band.band.eventos.infrastructure.persistence;

import br.com.band.band.eventos.domain.model.EventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private LocalDateTime dateTime;

    private String location;

    private String notes;

    private UUID setlistId;

    protected EventEntity() {
    }

    public EventEntity(
            UUID id,
            EventType type,
            LocalDateTime dateTime,
            String location,
            String notes,
            UUID setlistId
    ) {
        this.id = id;
        this.type = type;
        this.dateTime = dateTime;
        this.location = location;
        this.notes = notes;
        this.setlistId = setlistId;
    }

    public UUID getId() {
        return id;
    }

    public EventType getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public UUID getSetlistId() {
        return setlistId;
    }
}
