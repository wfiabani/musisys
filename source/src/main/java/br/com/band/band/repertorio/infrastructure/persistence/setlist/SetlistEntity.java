package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "setlists")
public class SetlistEntity {

    @Id
    private UUID id;

    private String name;

    @OneToMany(
            mappedBy = "setlist",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SetlistItemEntity> items;

    protected SetlistEntity() {}

    public SetlistEntity(br.com.band.band.repertorio.domain.setlist.model.Setlist setlist) {
        this.id = setlist.getId();
        this.name = setlist.getName();
        this.items = setlist.getItems()
                .stream()
                .map(item -> new SetlistItemEntity(item, this))
                .toList();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SetlistItemEntity> getItems() {
        return items;
    }
}
