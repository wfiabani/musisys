package br.com.band.band.repertorio.domain.setlist.model;

import java.util.UUID;

public class SetlistItem {

    private final UUID musicId;
    private final int position;

    public SetlistItem(UUID musicId, int position) {
        this.musicId = musicId;
        this.position = position;
    }

    public UUID getMusicId() {
        return musicId;
    }

    public int getPosition() {
        return position;
    }
}
