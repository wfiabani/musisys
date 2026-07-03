package br.com.band.band.profissionais.domain.model;

import java.util.UUID;

public class Profissional {

    private UUID id;
    private String name;
    private String role;
    private String description;
    private boolean isDefault;

    public Profissional(UUID id, String name, String role, String description, boolean isDefault) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.description = description;
        this.isDefault = isDefault;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
