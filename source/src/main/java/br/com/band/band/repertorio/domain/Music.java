package br.com.band.band.repertorio.domain;

public class Music {

    private final String id;
    private String name;
    private String key;

    public Music(String id, String name, String key) {
        this.id = id;
        this.name = name;
        this.key = key;
    }

    public void changeKey(String newKey) {
        this.key = newKey;
    }
}
