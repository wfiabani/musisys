package br.com.band.band.repertorio.domain.model;

import java.util.*;

public class Setlist {

    private final UUID id;
    private String name;
    private final List<SetlistItem> items = new ArrayList<>();

    public Setlist(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMusic(UUID musicId) {
        int nextPosition = items.size() + 1;
        items.add(new SetlistItem(musicId, nextPosition));
    }

    public void removeMusic(UUID musicId) {
        items.removeIf(item -> item.getMusicId().equals(musicId));
        normalizePositions();
    }

    public void moveMusic(UUID musicId, int newPosition) {
        if (newPosition < 1 || newPosition > items.size()) {
            throw new IllegalArgumentException("Invalid position");
        }

        SetlistItem item = items.stream()
                .filter(i -> i.getMusicId().equals(musicId))
                .findFirst()
                .orElseThrow();

        items.remove(item);
        items.add(newPosition - 1, item);

        normalizePositions();
    }

    private void normalizePositions() {
        for (int i = 0; i < items.size(); i++) {
            items.set(i, new SetlistItem(
                    items.get(i).getMusicId(),
                    i + 1
            ));
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SetlistItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
