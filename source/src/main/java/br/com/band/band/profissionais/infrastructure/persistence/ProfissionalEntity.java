package br.com.band.band.profissionais.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "professionals")
public class ProfissionalEntity {

    @Id
    private UUID id;

    private String name;
    private String role;

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean isDefault;

    protected ProfissionalEntity() {}

    public ProfissionalEntity(UUID id, String name, String role, String description, boolean isDefault) {
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
