package br.com.band.band.repertorio.infrastructure.persistence.setlist;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "setlist_items")
public class SetlistItemEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID musicId;
    private String musicName;
    private int position;

    @ManyToOne(optional = false)
    @JoinColumn(name = "setlist_id")
    private SetlistEntity setlist;

    protected SetlistItemEntity() {}

    public SetlistItemEntity(
            br.com.band.band.repertorio.domain.setlist.model.SetlistItem item,
            SetlistEntity setlist
    ) {
        this.musicId = item.getMusicId();
        this.position = item.getPosition();
        this.setlist = setlist;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMusicId() {
        return musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public int getPosition() {
        return position;
    }
}
