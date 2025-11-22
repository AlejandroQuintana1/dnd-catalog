package com.aqe.dnd.dnd_catalog.features.character.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "characters", schema = "dnd")
public class CharacterEntity implements Persistable<UUID> {

    @Id
    @Column(name = "character_id", nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "race", nullable = false)
    private String race;

    @Column(name = "character_class", nullable = false)
    private String characterClass;

    @Column(nullable = false)
    private Short level;

    @Column(name = "background", nullable = true)
    private String background;

    @Column(name = "alignment", nullable = false)
    private String alignment;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Column(name = "hit_points", nullable = false)
    private Integer hitPoints;

    @Column(name = "armor_class", nullable = false)
    private Integer armorClass;

    @Column(name = "initiative_misc", nullable = false)
    private Short initiativeMisc;

    @Column(name = "notes", nullable = true)
    private String notes;

    @Column(name = "grp", nullable = true)
    private String grp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chrt_id", nullable = false)
    private CharacterTypeEntity characterType; // Relación con la entidad de tipo de personaje

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = true)
    private PartyEntity party; // Relación con la entidad de la party

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Transient
    private boolean isNew = false;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        // Estrategia sencilla: si createdAt es null, lo consideramos nuevo
        // o si hemos marcado explícitamente isNew = true
        return this.isNew || this.createdAt == null;
    }

    public void markNew() {
        this.isNew = true;
    }

}
