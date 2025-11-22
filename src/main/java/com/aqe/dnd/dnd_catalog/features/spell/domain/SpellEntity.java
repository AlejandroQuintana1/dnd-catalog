package com.aqe.dnd.dnd_catalog.features.spell.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "spell", schema = "dnd")
public class SpellEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spell_id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Short level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private SpellSchoolEntity school;

    @Column(name = "casting_time", nullable = false)
    private String castingTime;

    @Column(nullable = false)
    private String range;

    @Column(nullable = false)
    private String components;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
}
