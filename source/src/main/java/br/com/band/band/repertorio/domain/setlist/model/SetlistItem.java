package br.com.band.band.repertorio.domain.setlist.model;

import java.util.UUID;

public class SetlistItem {

    private final UUID musicId;
    private final String musicName;

    public SetlistItem(UUID musicId, String musicName) {
        this.musicId = musicId;
        this.musicName = musicName;
    }

    public UUID getMusicId() {
        return musicId;
    }

    public String getMusicName() {
        return musicName;
    }
}
