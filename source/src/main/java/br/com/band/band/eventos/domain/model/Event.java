package br.com.band.band.eventos.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event {

    private UUID id;
    private EventType type;
    private LocalDateTime dateTime;
    private String location;
    private String notes;
    private UUID setlistId;

    public Event(
            UUID id,
            EventType type,
            LocalDateTime dateTime,
            String location,
            String notes
    ) {
        this.id = id;
        this.type = type;
        this.dateTime = dateTime;
        this.location = Objects.requireNonNull(location);
        this.notes = notes;
    }

    public void changeDateTime(LocalDateTime newDateTime) {
        this.dateTime = Objects.requireNonNull(newDateTime);
    }

    public void changeLocation(String newLocation) {
        this.location = Objects.requireNonNull(newLocation);
    }

    public void updateNotes(String notes) {
        this.notes = notes;
    }

    public void attachSetlist(UUID setlistId) {
        this.setlistId = Objects.requireNonNull(setlistId);
    }

    public void detachSetlist() {
        this.setlistId = null;
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
