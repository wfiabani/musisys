package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import br.com.band.band.repertorio.domain.model.Setlist;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import java.util.Collections;

@Entity
@Table(name = "setlists")
public class SetlistEntity {

    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "setlist")
    private List<SetlistItemEntity> items;

    protected SetlistEntity() {}

    public SetlistEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.items = Collections.emptyList();
    }

    public SetlistEntity(Setlist setlist) {
        this(setlist.getId(), setlist.getName());
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
