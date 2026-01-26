package br.com.band.band.repertorio.infrastructure.persistence.music;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "musics")
public class MusicEntity {

    @Id
    private UUID id;

    private String title;
    private String musicalKey;
    private String author;

    protected MusicEntity() {}

    public MusicEntity(UUID id, String title, String musicalKey, String author) {
        this.id = id;
        this.title = title;
        this.musicalKey = musicalKey;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMusicalKey() {
        return musicalKey;
    }

    public String getAuthor() {
        return author;
    }
}
