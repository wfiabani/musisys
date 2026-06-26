package br.com.band.band.repertorio.domain.model;

import java.util.UUID;

public class Music {

    private UUID id;
    private String title;
    private String key;
    private String author;
    private String description;

    public Music(UUID id, String title, String key, String author, String description) {
        this.id = id;
        this.title = title;
        this.key = key;
        this.author = author;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
