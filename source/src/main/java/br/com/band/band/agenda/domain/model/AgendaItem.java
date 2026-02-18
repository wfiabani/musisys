package br.com.band.band.agenda.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class AgendaItem {

    private UUID id;
    private AgendaItemType type;
    private String description;
    private LocalDateTime dateTime;

    public AgendaItem(UUID id,
                      AgendaItemType type,
                      String description,
                      LocalDateTime dateTime) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    public UUID getId() { return id; }
    public AgendaItemType getType() { return type; }
    public String getDescription() { return description; }
    public LocalDateTime getDateTime() { return dateTime; }
}
