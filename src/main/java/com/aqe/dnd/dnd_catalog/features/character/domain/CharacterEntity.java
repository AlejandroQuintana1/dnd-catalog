package com.aqe.dnd.dnd_catalog.features.character.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "characters", schema = "dnd")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="character_id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "race", nullable = false)
    private String race;

    @Column(name = "character_class", nullable = false)
    private String characterClass;

    @Column(nullable = false)
    private Short level;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Column(name = "hit_points", nullable = false)
    private Integer hitPoints;

    @Column(name = "armor_class", nullable = false)
    private Integer armorClass;

    @Column(name = "initiative_misc", nullable = false)
    private Short initiativeMisc;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

}
