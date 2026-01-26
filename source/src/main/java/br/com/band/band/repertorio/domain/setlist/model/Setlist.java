package br.com.band.band.repertorio.domain.setlist.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Setlist {

    private final UUID id;
    private String name;
    private LocalDate date;
    private String notes;
    private final List<SetlistItem> items;

    public Setlist(
            UUID id,
            String name,
            LocalDate date,
            String notes,
            List<SetlistItem> items
    ) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.notes = notes;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public List<SetlistItem> getItems() {
        return items;
    }
}