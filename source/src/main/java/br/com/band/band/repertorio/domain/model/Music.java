package br.com.band.band.repertorio.domain.model;

import java.util.UUID;

public class Music {

    private UUID id;
    private String title;
    private String key;
    private String author;

    public Music(UUID id, String title, String key, String author) {
        this.id = id;
        this.title = title;
        this.key = key;
        this.author = author;
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
}
